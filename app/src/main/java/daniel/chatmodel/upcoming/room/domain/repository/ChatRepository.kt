package daniel.chatmodel.upcoming.room.domain.repository

import daniel.chatmodel.upcoming.room.domain.model.ChatPreview
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getAllChatPreviews(): Flow<List<ChatPreview>>
    suspend fun saveChat(chatTitle: String)
}