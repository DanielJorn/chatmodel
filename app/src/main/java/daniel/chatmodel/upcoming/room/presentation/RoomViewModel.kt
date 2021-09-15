package daniel.chatmodel.upcoming.room.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.chatmodel.R
import daniel.chatmodel.upcoming.room.domain.model.ChatPreview
import daniel.chatmodel.upcoming.room.domain.usecase.GetAllChatPreviewsUseCase
import daniel.chatmodel.upcoming.room.domain.usecase.SaveUserUseCase
import daniel.chatmodel.upcoming.room.presentation.recycleradapter.RecyclerItem
import daniel.chatmodel.upcoming.room.presentation.recycleradapter.RoomChatPreviewClickListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "RoomViewModel"

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getAllChatPreviewsUseCase: GetAllChatPreviewsUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    val userNameObservable: MutableLiveData<String> = MutableLiveData("")
    val userSurnameObservable: MutableLiveData<String> = MutableLiveData("")

    private val userName get() = userNameObservable.value ?: ""
    private val userSurname get() = userSurnameObservable.value ?: ""

    private val _chatPreviewList: MutableLiveData<List<RecyclerItem>> = MutableLiveData(listOf())
    val chatPreviewList: LiveData<List<RecyclerItem>> = _chatPreviewList

    init {
        //leave it for now
        //loadChatPreviewList()
    }

    fun onConfirmClicked() {
        viewModelScope.launch {
            when (saveUserUseCase.saveUser(userName, userSurname)) {
                is SaveUserUseCase.Result.Success -> {
                    //do smthng
                }
                else -> {
                    //do smthng else
                }
            }
        }
    }

    private fun loadChatPreviewList() {
        viewModelScope.launch {
            getAllChatPreviewsUseCase.getAllChatPreviews().collect { result ->
                when (result) {
                    is GetAllChatPreviewsUseCase.Result.Success -> {
                        _chatPreviewList.value = result.chatPreviews.map { it.toRecyclerItem() }
                    }
                    else -> {
                        //do smthng
                    }
                }
            }
        }
    }

    private fun onChatPreviewClicked(chatPreview: ChatPreview) {
        Log.d(TAG, "onChatPreviewClicked =)")
    }

    private fun ChatPreview.toRecyclerItem(): RecyclerItem {
        return RecyclerItem(this, R.layout.item_room_chat_preview, chatPreviewClickListener)
    }

    private val chatPreviewClickListener = object : RoomChatPreviewClickListener {
        override fun onChatPreviewClicked(chatPreview: ChatPreview) =
            this@RoomViewModel.onChatPreviewClicked(chatPreview)
    }
}