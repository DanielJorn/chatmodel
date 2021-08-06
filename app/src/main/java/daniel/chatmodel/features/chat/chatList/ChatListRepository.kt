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
import daniel.chatmodel.features.chat.ChatModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

private const val TAG = "ChatListRepository"

class ChatListRepository {
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val listenerList = arrayListOf<ListenerRegistration>()

    fun loadChatList(): Flow<State<List<ChatPreviewModel>>> {
        return callbackFlow {
            trySend(Loading)

            val listener = db.collection(CHATS)
                .addSnapshotListener { snapshot, error ->
                    if (error != null){
                        trySend(Failure(error))
                        return@addSnapshotListener
                    }
                    if (snapshot == null){
                        // ???
                        return@addSnapshotListener
                    }
                    val dbChatList = snapshot.toObjects(ChatModel::class.java)
                    val uiChatList = dbChatList.map { ChatPreviewModel(it.id, it.title) }

                    trySend(Success(uiChatList))
                }

            listenerList.add(listener)

            awaitClose {
                listener.remove()
                Log.d(TAG, "loadChatList: removed chat listener")
            }
        }
    }

    fun onCleared(){
        listenerList.forEach {
            it.remove()
        }
    }
}

