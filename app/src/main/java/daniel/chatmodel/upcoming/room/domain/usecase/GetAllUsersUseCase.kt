package daniel.chatmodel.upcoming.room.domain.usecase

import daniel.chatmodel.upcoming.room.domain.model.User
import daniel.chatmodel.upcoming.room.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    sealed interface Result {
        data class Success(val users: List<User>) : Result
        data class Failure(val exception: Exception) : Result
    }

    fun getAllUsers(): Flow<Result> {
        return userRepository.getAllUsers().map { Result.Success(it) }
    }
}