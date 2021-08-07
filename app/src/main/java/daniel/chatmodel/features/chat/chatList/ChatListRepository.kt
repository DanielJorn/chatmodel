package daniel.chatmodel.features.chat.chatList

import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.base.Failure
import daniel.chatmodel.base.Loading
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
    private val db = Firebase.firestore
    private var mainListener: ListenerRegistration? = null
    private val listenerMap: MutableMap<String, ListenerRegistration> = mutableMapOf()

    private val chatMap: MutableMap<String, ChatPreviewModel> = mutableMapOf()

    private var onUpdate: (State<List<ChatPreviewModel>>) -> Unit = { }

    fun loadChatList(): Flow<State<List<ChatPreviewModel>>> = callbackFlow {
        onUpdate = { state -> trySend(state) }

        trySend(Loading)

        val listener = db.collection(CHATS)
            .handleUpdates(::handleNewChatList, ::handleError)

        mainListener = listener

        awaitClose {
            onCleared()
        }
    }

    private fun handleNewChatList(snapshot: QuerySnapshot?) {
        val dbChatList = snapshot!!.toObjects(ChatModel::class.java)
        dbChatList.forEach {
            it?.let {
                addLastMessageListener(it)
                addChatWithoutLastMessage(it)
            }
        }
    }

    private fun addLastMessageListener(chatModel: ChatModel) {
        val lastMessageListener = listenerMap[chatModel.id]
        val noLastMessageListenerAttached = lastMessageListener == null

        if (noLastMessageListenerAttached) {
            val listener = db.collection(CHATS)
                .document(chatModel.id)
                .collection("messages")
                .orderBy("sent", Query.Direction.DESCENDING)
                .limit(1)
                .handleUpdates(
                    onSuccess = { handleLastMessage(it, chatModel) },
                    onFailure = { onUpdate(Failure(it)) },
                    allowNullSnapshot = true
                )

            listenerMap[chatModel.id] = listener
        }
    }

    private fun handleLastMessage(snapshot: QuerySnapshot?, chatModel: ChatModel) {
        if (snapshot == null) {
            addChatWithoutLastMessage(chatModel)
            return
        }

        if (snapshot.size() == 0) {
            addChatWithoutLastMessage(chatModel)
            return
        }

        val lastMessageDocument = snapshot.documents[0]
        val lastMessage = lastMessageDocument.toObject(MessageModel::class.java)
        if (lastMessage == null) {
            addChatWithoutLastMessage(chatModel)
            return
        }

        val uiChat = ChatPreviewModel(chatModel.id, chatModel.title, lastMessage.text)
        addChat(uiChat)
    }

    private fun addChat(uiChat: ChatPreviewModel) {
        chatMap[uiChat.id] = uiChat
        onUpdate(Success(chatMap.values.toList()))
    }

    private fun addChatWithoutLastMessage(chatModel: ChatModel) {
        val uiChat = ChatPreviewModel(chatModel.id, chatModel.title, "")
        addChat(uiChat)
    }

    private fun handleError(exception: Exception) = onUpdate(Failure(exception))

    fun onCleared() {
        mainListener?.let {
            it.remove()
            Log.d(TAG, "onCleared: removed main listener")
        }

        val listenerToRemove = listenerMap.values.count()
        listenerMap.values.forEach {
            it.remove()
        }

        Log.d(TAG, "onCleared: removed $listenerToRemove listeners")
    }
}

