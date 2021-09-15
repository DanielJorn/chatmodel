package daniel.chatmodel.upcoming.room.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import daniel.chatmodel.upcoming.room.data.models.ChatEntity

@Database(entities = [ChatEntity::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {
    abstract fun chatDao(): ChatDao
}