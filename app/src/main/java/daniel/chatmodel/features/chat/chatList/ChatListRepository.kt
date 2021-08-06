package daniel.chatmodel.features.chat.chatList

import android.util.Log
import com.google.firebase.auth.ktx.auth
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
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

private const val TAG = "ChatListRepository"

class ChatListRepository {
    private val chatMap : MutableMap<String, ChatModel> = mutableMapOf()
    private val database = Firebase.firestore
    private var onUpdate: () -> Unit = { }

    fun loadChatList(): Flow<State<List<ChatModel>>> = callbackFlow {
        onUpdate = { this.trySend(Success(chatMap.values.toList())) }
        val listener = database.collection(CHATS)
            .handleUpdates(::onChatUpdate, ::onChatUpdateFailed)

        awaitClose {
            listener.remove()
        }
    }

    private fun onChatUpdate(snapshot: QuerySnapshot) {
        snapshot.documentChanges.forEach {
            val chatPreview = it.document.toObject(ChatModel::class.java)

            when (it.type) {
                DocumentChange.Type.ADDED -> onChatAdded(chatPreview)
                DocumentChange.Type.MODIFIED -> onChatModified(chatPreview)
                DocumentChange.Type.REMOVED -> onChatRemoved(chatPreview)
            }
        }
    }


    private fun onChatAdded(chatPreview: ChatModel) {
        chatMap[chatPreview.id] = chatPreview
        onUpdate()
    }

    private fun onChatModified(modifiedModel: ChatModel) {
        chatMap[modifiedModel.id] = modifiedModel
        onUpdate()
    }

    private fun onChatRemoved(chatPreview: ChatModel) {
        chatMap.remove(chatPreview.id)
        onUpdate()
    }

    private fun onChatUpdateFailed(exception: FirebaseFirestoreException) = Unit

}

