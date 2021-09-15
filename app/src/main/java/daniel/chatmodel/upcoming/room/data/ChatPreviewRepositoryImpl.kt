package daniel.chatmodel.upcoming.room.data

import daniel.chatmodel.upcoming.room.domain.model.ChatPreview
import daniel.chatmodel.upcoming.room.domain.repository.ChatPreviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatPreviewRepositoryImpl @Inject constructor(): ChatPreviewRepository {
    override fun getAllChatPreviews(): Flow<List<ChatPreview>> {
        TODO()
    }
}