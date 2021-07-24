package daniel.chatmodel.features.chatList

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

private const val TAG = "ChatListViewModel"

class ChatListViewModel : ViewModel() {
    val chatList = ArrayList<FirebaseChatModel>()
    private val mutableUpdateLiveData = MutableLiveData<Unit>()
    val updateLiveData: LiveData<Unit> = mutableUpdateLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> = errorLiveData

    private val database = Firebase.firestore

    fun updateChatList() {
        database.collection(CHATS)
            .handleUpdates(::onChatUpdate, ::onChatUpdateFailed)
    }

    private fun onChatUpdate(snapshot: QuerySnapshot) {
        snapshot.documentChanges.forEach {
            val chatPreview = it.document.toObject(FirebaseChatModel::class.java)

            when (it.type) {
                DocumentChange.Type.ADDED -> onChatAdded(chatPreview)
                DocumentChange.Type.MODIFIED -> onChatModified(chatPreview)
                DocumentChange.Type.REMOVED -> onChatRemoved(chatPreview)
            }
        }
    }

    private fun onChatUpdateFailed(exception: FirebaseFirestoreException) = Unit

    private fun onChatAdded(chatPreview: FirebaseChatModel) {
        chatList.add(chatPreview)
        mutableUpdateLiveData.notificate()
    }

    private fun onChatModified(modifiedModel: FirebaseChatModel) {
        updateChat(modifiedModel)
        mutableUpdateLiveData.notificate()
    }

    private fun updateChat(chatToUpdate: FirebaseChatModel) {
        val indexToUpdate = indexOf(chatToUpdate)
        if (indexToUpdate >= 0)
            chatList[indexToUpdate] = chatToUpdate
    }

    private fun indexOf(chatToFind: FirebaseChatModel): Int {
        val nullPos = -1
        var neededIndex = nullPos

        chatList.forEachIndexed { currInd, currChatModel ->
            if (currChatModel.id == chatToFind.id)
                neededIndex = currInd

            if (neededIndex != nullPos) {
                return neededIndex
            }
        }

        return neededIndex
    }

    private fun onChatRemoved(chatPreview: FirebaseChatModel) {
        chatList.removeAll { it.id == chatPreview.id }
        mutableUpdateLiveData.notificate()
    }
}
