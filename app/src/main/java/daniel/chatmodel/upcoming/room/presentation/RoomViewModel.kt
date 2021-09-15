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
import daniel.chatmodel.upcoming.room.domain.usecase.SaveChatUseCase
import daniel.chatmodel.upcoming.room.presentation.recycleradapter.RecyclerItem
import daniel.chatmodel.upcoming.room.presentation.recycleradapter.RoomChatPreviewClickListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "RoomViewModel"

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getAllChatPreviewsUseCase: GetAllChatPreviewsUseCase,
    private val saveChatUseCase: SaveChatUseCase
) : ViewModel() {
    val chatTitleObservable: MutableLiveData<String> = MutableLiveData("")

    private val chatTitle get() = chatTitleObservable.value ?: ""

    private val _chatPreviewList: MutableLiveData<List<RecyclerItem>> = MutableLiveData(listOf())
    val chatPreviewList: LiveData<List<RecyclerItem>> = _chatPreviewList

    init {
        loadChatPreviewList()
    }

    fun onAddChatClicked() {
        viewModelScope.launch {
            when (val result = saveChatUseCase.saveChat(chatTitle)) {
                is SaveChatUseCase.Result.Success -> {
                    Log.d(TAG, "onAddChatClicked: success, $result")
                }
                is SaveChatUseCase.Result.Failure -> {
                    Log.e(TAG, "onAddChatClicked: error", result.exception)
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