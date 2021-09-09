package daniel.chatmodel.upcoming.room.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import daniel.chatmodel.upcoming.room.data.models.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}