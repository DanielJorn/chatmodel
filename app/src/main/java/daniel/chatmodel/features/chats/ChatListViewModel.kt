package daniel.chatmodel.features.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "ChatListViewModel"

class ChatListViewModel : ViewModel() {
    val chatList = ArrayList<ChatPreviewModel>()
    private val mutableUpdateLiveData = MutableLiveData<Unit>()
    val updateLiveData: LiveData<Unit> = mutableUpdateLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> = errorLiveData

    private val database = Firebase.firestore

    fun updateChatList() {
        database.collection("chats")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    handleError();
                    return@addSnapshotListener
                }

                snapshot?.documentChanges?.forEach {
                    val chatPreview = it.document.toObject(ChatPreviewModel::class.java)

                    when (it.type) {
                        DocumentChange.Type.ADDED -> onChatAdded(chatPreview)
                        DocumentChange.Type.MODIFIED -> onChatModified(chatPreview)
                        DocumentChange.Type.REMOVED -> onChatRemoved(chatPreview)
                    }
                }
            }
    }

    private fun onChatRemoved(chatPreview: ChatPreviewModel) {
        chatList.removeAll { it.id == chatPreview.id }
        mutableUpdateLiveData.value = mutableUpdateLiveData.value

    }

    private fun onChatModified(modifiedModel: ChatPreviewModel) {
        chatList.forEachIndexed { currInd, currChatModel ->
            val nullPos = -1
            var indToChange = nullPos

            if (currChatModel.id == modifiedModel.id)
                indToChange = currInd

            if (indToChange != nullPos) {
                chatList[indToChange] = modifiedModel
                return@forEachIndexed
            }
        }

        mutableUpdateLiveData.value = mutableUpdateLiveData.value
    }

    private fun onChatAdded(chatPreview: ChatPreviewModel) {
        chatList.add(chatPreview)
        mutableUpdateLiveData.value = mutableUpdateLiveData.value
    }

    private fun handleError() {

    }
}