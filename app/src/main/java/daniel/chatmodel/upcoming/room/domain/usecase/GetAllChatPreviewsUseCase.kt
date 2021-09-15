package daniel.chatmodel.upcoming.room.domain.usecase

import daniel.chatmodel.upcoming.room.domain.model.ChatPreview
import daniel.chatmodel.upcoming.room.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllChatPreviewsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    sealed interface Result {
        data class Success(val chatPreviews: List<ChatPreview>) : Result
        data class Failure(val exception: Exception) : Result
    }

    fun getAllChatPreviews(): Flow<Result> {
        return chatRepository.getAllChatPreviews().map { Result.Success(it) }
    }
}