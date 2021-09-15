package daniel.chatmodel.upcoming.room.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import daniel.chatmodel.upcoming.room.data.models.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats")
    fun getAllChats(): Flow<List<ChatEntity>>

    @Insert
    suspend fun insertChat(chatEntity: ChatEntity)
}