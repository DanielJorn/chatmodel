package daniel.chatmodel.upcoming.room.domain.usecase

import daniel.chatmodel.upcoming.room.domain.model.ChatPreview
import daniel.chatmodel.upcoming.room.domain.repository.ChatPreviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllChatPreviewsUseCase @Inject constructor(
    private val chatPreviewRepository: ChatPreviewRepository
) {
    sealed interface Result {
        data class Success(val chatPreviews: List<ChatPreview>) : Result
        data class Failure(val exception: Exception) : Result
    }

    fun getAllChatPreviews(): Flow<Result> {
        return chatPreviewRepository.getAllChatPreviews().map { Result.Success(it) }
    }
}