package daniel.chatmodel.base

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notificate(){
    value = value
}