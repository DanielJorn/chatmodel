package daniel.chatmodel.upcoming.room.data

import daniel.chatmodel.upcoming.room.domain.model.ChatPreview
import daniel.chatmodel.upcoming.room.domain.repository.ChatPreviewRepository
import kotlinx.coroutines.flow.Flow

class ChatPreviewRepositoryImpl: ChatPreviewRepository {
    override fun getAllChatPreviews(): Flow<List<ChatPreview>> {
        TODO()
    }
}