package daniel.chatmodel.upcoming.room.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import daniel.chatmodel.upcoming.room.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val surname: String,
)

fun UserEntity.toDomainModel(): User {
    return User(
        id,
        name,
        surname
    )
}
