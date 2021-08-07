package daniel.chatmodel.features.chat.chatList

import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.base.Loading
import daniel.chatmodel.base.State
import daniel.chatmodel.base.Success
import daniel.chatmodel.base.firestore.CHATS
import daniel.chatmodel.base.firestore.handleUpdates
import daniel.chatmodel.features.chat.ChatModel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

private const val TAG = "ChatListRepository"

class ChatListRepository {
    private val db = Firebase.firestore
    private var mainListener: ListenerRegistration? = null

    private var onUpdate: (State<List<ChatPreviewModel>>) -> Unit = { }

    fun loadChatList(): Flow<State<List<ChatPreviewModel>>> = callbackFlow {
        onUpdate = { state -> trySend(state) }

        trySend(Loading)

        val listener = db.collection(CHATS)
            .handleUpdates(::handleNewChatList, ::handleError)

        mainListener = listener

        awaitClose {
            listener.remove()
            Log.d(TAG, "loadChatList: removed chat listener")
        }
    }

    private fun handleNewChatList(snapshot: QuerySnapshot) {
        val dbChatList = snapshot.toObjects(ChatModel::class.java)
        val uiChatList = dbChatList.map { ChatPreviewModel(it.id, it.title) }

        onUpdate(Success(uiChatList))
    }

    private fun handleError(exception: Exception) = Unit

    fun onCleared() {
        mainListener?.let {
            it.remove()
            Log.d(TAG, "onCleared: removed main listener")
        }
    }
}

