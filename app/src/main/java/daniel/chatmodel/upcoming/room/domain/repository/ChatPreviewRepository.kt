package daniel.chatmodel.upcoming.room.domain.repository

import daniel.chatmodel.upcoming.room.domain.model.ChatPreview
import kotlinx.coroutines.flow.Flow

interface ChatPreviewRepository {
    fun getAllChatPreviews(): Flow<List<ChatPreview>>
}