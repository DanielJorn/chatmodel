package daniel.chatmodel.upcoming.room.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import daniel.chatmodel.upcoming.room.domain.model.ChatPreview

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "chat_title") val chatTitle: String
)

fun ChatEntity.toDomainModel(): ChatPreview {
    return ChatPreview(id, chatTitle)
}