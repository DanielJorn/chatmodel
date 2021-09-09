package daniel.chatmodel.upcoming.room.domain.usecase

import daniel.chatmodel.upcoming.room.domain.model.User
import daniel.chatmodel.upcoming.room.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    sealed interface Result {
        object Success: Result
        data class Failure(val exception: Exception) : Result
    }

    suspend fun saveUser(userName: String, userSurname: String) {
        userRepository.saveUser(userName, userSurname)
    }
}