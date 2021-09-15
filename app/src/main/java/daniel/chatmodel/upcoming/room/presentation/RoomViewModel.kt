package daniel.chatmodel.upcoming.room.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.chatmodel.R
import daniel.chatmodel.upcoming.room.domain.usecase.GetAllUsersUseCase
import daniel.chatmodel.upcoming.room.domain.model.User
import daniel.chatmodel.upcoming.room.domain.usecase.SaveUserUseCase
import daniel.chatmodel.upcoming.room.presentation.recycleradapter.RecyclerItem
import daniel.chatmodel.upcoming.room.presentation.recycleradapter.UserClickListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "RoomViewModel"

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    val userNameObservable: MutableLiveData<String> = MutableLiveData("")
    val userSurnameObservable: MutableLiveData<String> = MutableLiveData("")

    private val userName get() = userNameObservable.value ?: ""
    private val userSurname get() = userSurnameObservable.value ?: ""

    private val _userList: MutableLiveData<List<RecyclerItem>> = MutableLiveData(listOf())
    val userList: LiveData<List<RecyclerItem>> = _userList

    init {
        loadUserList()
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

    private fun loadUserList() {
        viewModelScope.launch {
            getAllUsersUseCase.getAllUsers().collect { result ->
                when (result) {
                    is GetAllUsersUseCase.Result.Success -> {
                        _userList.value = result.users.map { it.toRecyclerItem() }
                    }
                    else -> {
                        //do smthng
                    }
                }
            }
        }
    }

    private fun User.toRecyclerItem(): RecyclerItem {
        return RecyclerItem(this, R.layout.item_room_user, listener)
    }

    private val listener = object: UserClickListener {
        override fun onUserClicked() {
            Log.d(TAG, "onUserClicked ^_^")
        }

        override fun onIconClicked() {
            Log.d(TAG, "onIconClicked ^_^")
        }
    }
}