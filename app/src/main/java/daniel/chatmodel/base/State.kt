package daniel.chatmodel.base

sealed class State<out T : Any>

data class Success<out T : Any>(val data: T) : State<T>()
data class Failure(val reason: Exception) : State<Nothing>()
object Loading : State<Nothing>()