package daniel.chatmodel.upcoming.room.data

import daniel.chatmodel.upcoming.room.data.database.ChatDao
import daniel.chatmodel.upcoming.room.data.models.ChatEntity
import daniel.chatmodel.upcoming.room.data.models.toDomainModel
import daniel.chatmodel.upcoming.room.domain.model.ChatPreview
import daniel.chatmodel.upcoming.room.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao
) : ChatRepository {
    override fun getAllChatPreviews(): Flow<List<ChatPreview>> {
        return chatDao.getAllChats().map { list ->
            list.map { chat -> chat.toDomainModel() }
        }
    }

    override suspend fun saveChat(chatTitle: String) {
        val chatEntity = ChatEntity(UUID.randomUUID().toString(), chatTitle)
        chatDao.insertChat(chatEntity)
    }
}