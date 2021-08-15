package daniel.chatmodel.features.chat.chatList

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "FirebaseChatListRepo"

class FirebaseChatListRepository @Inject constructor(
    private val messageRepository: MessageRepository
) : ChatListRepository {
    private val database = Firebase.firestore

    private val chatMap: MutableMap<String, ChatPreviewModel> = mutableMapOf()
    private val lastMessageJobMap: MutableMap<String, Job> = mutableMapOf()
    private var mainListener: ListenerRegistration? = null

    private var onChatListUpdate: (State<List<ChatPreviewModel>>) -> Unit = { }

    override fun loadChatList(): Flow<State<List<ChatPreviewModel>>> = callbackFlow {
        onChatListUpdate = { this.trySend(it) }

        mainListener = database
            .collection(CHATS)
            .handleUpdates(::onChatUpdate, ::onChatUpdateFailed)

        awaitClose {
            Log.d(TAG, "awaitClose: removing all listeners")
            onCleared()
        }
    }

    override fun onCleared() {
        mainListener?.let {
            it.remove()
            Log.d(TAG, "onCleared: removed main listener")
        }

        var jobCount = 0
        lastMessageJobMap.values.forEach {
            it.cancel()
            ++jobCount
        }
        Log.d(TAG, "onCleared: canceled $jobCount jobs")
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
        val lastMessageFlow = messageRepository.listenLastMessage(chatModel.id)
        val lastMessageJob = GlobalScope.launch {
            lastMessageFlow.collect {
                onLastMessageLoaded(it, chatModel)
            }
        }
        lastMessageJobMap[chatModel.id] = lastMessageJob
    }

    private fun onLastMessageLoaded(messageState: State<MessageModel>, chatModel: ChatModel) {
        when (messageState) {
            is Success -> {
                val lastMessage = messageState.data
                val uiChat = ChatPreviewModel(chatModel.id, chatModel.title, lastMessage.text)

                chatMap[uiChat.id] = uiChat
                onChatListUpdate(Success(chatMap.values.toList()))
            }
            is Failure -> {
                Log.d(TAG, "onLastMessageLoaded: uh oh")
            }
            Loading -> {
                Log.d(TAG, "onLastMessageLoaded: loading")
            }
        }

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
        val lastMessageJob = lastMessageJobMap[chatPreview.id]
        lastMessageJob?.cancel()

        onChatListUpdate(Success(chatMap.values.toList()))
    }

    private fun onChatUpdateFailed(exception: FirebaseFirestoreException) = Unit
}