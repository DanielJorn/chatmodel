package daniel.chatmodel.upcoming.room.domain.repository

import daniel.chatmodel.upcoming.room.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    suspend fun saveUser(userName: String, userSurname: String)
}