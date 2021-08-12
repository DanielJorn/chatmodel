package daniel.chatmodel.features.chat.chatList

import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.base.State
import daniel.chatmodel.base.Success
import daniel.chatmodel.base.firestore.CHATS
import daniel.chatmodel.base.firestore.handleUpdates
import daniel.chatmodel.features.chat.ChatModel
import daniel.chatmodel.features.chat.MessageModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

private const val TAG = "ChatListRepository"

class ChatListRepository {
    private val chatMap: MutableMap<String, ChatPreviewModel> = mutableMapOf()
    private val listenerMap: MutableMap<String, MutableList<ListenerRegistration>> = mutableMapOf()
    private var mainListener: ListenerRegistration? = null

    private val database = Firebase.firestore
    private var onChatListUpdate: (State<List<ChatPreviewModel>>) -> Unit = { }

    fun loadChatList(): Flow<State<List<ChatPreviewModel>>> = callbackFlow {
        onChatListUpdate = { this.trySend(it) }

        mainListener = database.collection(CHATS)
            .handleUpdates(::onChatUpdate, ::onChatUpdateFailed)

        awaitClose {
            Log.d(TAG, "awaitClose: removing all listeners")
            onCleared()
        }
    }

    private fun onChatUpdate(snapshot: QuerySnapshot) {
        snapshot.documentChanges.forEach {
            val chatModel = it.document.toObject(ChatModel::class.java)

            when (it.type) {
                DocumentChange.Type.ADDED -> onChatAdded(chatModel)
                DocumentChange.Type.MODIFIED -> onChatModified(chatModel)
                DocumentChange.Type.REMOVED -> onChatRemoved(chatModel)
            }
        }
    }


    private fun onChatAdded(chatModel: ChatModel) {
        val listener = database.collection("chats")
            .document(chatModel.id)
            .collection("messages")
            .orderBy("sent", Query.Direction.DESCENDING)
            .limit(1)
            .addSnapshotListener { snapshot, error ->
                if (error != null) return@addSnapshotListener
                if (snapshot == null) return@addSnapshotListener

                onLastMessageLoaded(snapshot, chatModel)
            }
        if (listenerMap[chatModel.id] == null) {
            listenerMap[chatModel.id] = arrayListOf()
        }
        listenerMap[chatModel.id]!!.add(listener)
    }

    private fun onLastMessageLoaded(snapshot: QuerySnapshot, chatModel: ChatModel) {
        if (snapshot.size() == 0) return

        val lastMessageDocument = snapshot.documents[0]
        val lastMessage = lastMessageDocument.toObject(MessageModel::class.java) ?: return

        val uiChat = ChatPreviewModel(chatModel.id, chatModel.title, lastMessage.text)

        chatMap[uiChat.id] = uiChat
        onChatListUpdate(Success(chatMap.values.toList()))
    }

    private fun onChatModified(modifiedModel: ChatModel) {
        val chatPreviewBeforeUpdate = chatMap[modifiedModel.id]!!
        val uiChat = ChatPreviewModel(
            modifiedModel.id,
            modifiedModel.title,
            chatPreviewBeforeUpdate.lastMessageText
        )

        chatMap[modifiedModel.id] = uiChat
        onChatListUpdate(Success(chatMap.values.toList()))
    }

    private fun onChatRemoved(chatPreview: ChatModel) {
        chatMap.remove(chatPreview.id)

        Log.d(TAG, "onChatRemoved: removed listeners from chat: $chatPreview")
        listenerMap[chatPreview.id]?.forEach {
            it.remove()
        }
        onChatListUpdate(Success(chatMap.values.toList()))
    }

    private fun onChatUpdateFailed(exception: FirebaseFirestoreException) = Unit

    fun onCleared(){
        mainListener?.let {
            it.remove()
            Log.d(TAG, "onCleared: removed main listener")
        }

        var listenerCount = 0
        listenerMap.values.forEach {
            it.forEach { registration ->
                registration.remove()
                ++listenerCount
            }
        }
        Log.d(TAG, "onCleared: removed $listenerCount listeners")
    }
}

