package daniel.chatmodel.features.chat.chatList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.base.State
import daniel.chatmodel.base.Success
import daniel.chatmodel.base.firestore.CHATS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "ChatListViewModel"

class ChatListViewModel : ViewModel() {
    private val _chatList = MutableLiveData<State<List<ChatPreviewModel>>>()
    val chatList: LiveData<State<List<ChatPreviewModel>>> = _chatList

    private val chatListRepository = ChatListRepository()

    init {
        loadChatList()
    }

    private fun loadChatList() {
        viewModelScope.launch {
            chatListRepository.loadChatList().collect {
                when (it) {
                    is Success -> handleNewChatList(it.data)
                    else -> Log.d(TAG, "loadChatList: lazy")
                }
            }
        }
    }

    private fun handleNewChatList(list: List<ChatPreviewModel>) {
        _chatList.value = Success(list)
    }

    override fun onCleared() {
        //todo clear repository
    }
}