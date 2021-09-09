package daniel.chatmodel.upcoming.room.data

import daniel.chatmodel.upcoming.room.data.database.UserDao
import daniel.chatmodel.upcoming.room.data.models.UserEntity
import daniel.chatmodel.upcoming.room.data.models.toDomainModel
import daniel.chatmodel.upcoming.room.domain.model.User
import daniel.chatmodel.upcoming.room.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAll()
            .map { list ->
                list.map { userEntity -> userEntity.toDomainModel() }
            }
    }

    override suspend fun saveUser(userName: String, userSurname: String) {
        val entity = UserEntity(UUID.randomUUID().toString(), userName, userSurname)
        userDao.insert(entity)
    }
}

