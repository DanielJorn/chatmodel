package daniel.chatmodel.features.chat.chatList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.base.firestore.CHATS
import daniel.chatmodel.base.firestore.handleUpdates
import daniel.chatmodel.base.notificate
import daniel.chatmodel.features.chat.ChatModel

private const val TAG = "ChatListViewModel"

//todo better move all the database stuff outta here to repository class or something
// todo class itself is clunky but I did no research
class ChatListViewModel : ViewModel() {
    private val chatMap : MutableMap<String, ChatModel> = mutableMapOf()

    private val _chatList = MutableLiveData<List<ChatModel>>()
    val chatList: LiveData<List<ChatModel>> = _chatList

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> = errorLiveData

    private val database = Firebase.firestore

    fun updateChatList() {
        database.collection(CHATS)
            .handleUpdates(::onChatUpdate, ::onChatUpdateFailed)
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

    private fun onChatUpdateFailed(exception: FirebaseFirestoreException) = Unit

    private fun onChatAdded(chatPreview: ChatModel) {
        chatMap[chatPreview.id] = chatPreview
        _chatList.value = chatMap.values.toList()
    }

    private fun onChatModified(modifiedModel: ChatModel) {
        chatMap[modifiedModel.id] = modifiedModel
        _chatList.value = chatMap.values.toList()
    }

    private fun onChatRemoved(chatPreview: ChatModel) {
        chatMap.remove(chatPreview.id)
        _chatList.value = chatMap.values.toList()
    }
}
