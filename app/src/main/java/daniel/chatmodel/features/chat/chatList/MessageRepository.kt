package daniel.chatmodel.features.chat.chatList

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.base.Failure
import daniel.chatmodel.base.State
import daniel.chatmodel.base.Success
import daniel.chatmodel.base.firestore.CHATS
import daniel.chatmodel.base.firestore.MESSAGES
import daniel.chatmodel.base.firestore.SENT
import daniel.chatmodel.features.chat.MessageModel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.Exception

private const val TAG = "MessageRepository"

interface MessageRepository {
    fun listenLastMessage(chatId: String): Flow<State<MessageModel>>
}