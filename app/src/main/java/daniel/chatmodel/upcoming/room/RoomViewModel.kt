package daniel.chatmodel.upcoming.room

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import daniel.chatmodel.ChatApplication
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import java.util.*
import kotlinx.coroutines.flow.collect

private const val TAG = "RoomViewModel"

class RoomViewModel : ViewModel() {
    private val roomDB = ChatApplication.instance.database
    private val userDao = roomDB.userDao()

    val userName: MutableLiveData<String> = MutableLiveData("")
    val userSurname: MutableLiveData<String> = MutableLiveData("")

    // todo I use userList variable to hide MutableLiveData _userList and present it as LiveData (not mutable)
    // is there better way of doing smthng like this?
    private val _userList: MutableLiveData<List<User>> = MutableLiveData(listOf())
    val userList: LiveData<List<User>> = _userList

    fun onConfirmClicked() {
        viewModelScope.launch {
            val id = UUID.randomUUID().toString()

            //todo yep no check for validity of input fields
            val user = User(id, userName.value!!, userSurname.value!!)

            userDao.insert(user)
        }
    }

    fun loadUserList() {
        viewModelScope.launch {
            userDao.getAll().collect {
                _userList.value = it
            }
        }
    }
}