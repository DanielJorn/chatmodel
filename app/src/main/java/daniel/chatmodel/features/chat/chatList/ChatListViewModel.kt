package daniel.chatmodel.features.chat.chatList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.chatmodel.base.State
import daniel.chatmodel.base.Success
import daniel.chatmodel.base.firestore.CHATS
import daniel.chatmodel.features.chat.ChatModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ChatListViewModel"

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val chatListRepository: ChatListRepository
): ViewModel() {
    private val _chatList = MutableLiveData<List<ChatPreviewModel>>()
    val chatList: LiveData<List<ChatPreviewModel>> = _chatList

    init {
        loadChatList()
    }

    private fun loadChatList() {
        viewModelScope.launch {
            chatListRepository.loadChatList().collect {
                when (it) {
                    is Success -> _chatList.value = it.data
                    else -> {
                        // i don't know...
                    }
                }
            }
        }
    }

    override fun onCleared() {
        chatListRepository.onCleared()
    }
}