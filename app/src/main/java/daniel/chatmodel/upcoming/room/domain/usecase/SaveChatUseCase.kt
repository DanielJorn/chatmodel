package daniel.chatmodel.upcoming.room.domain.usecase

import daniel.chatmodel.upcoming.room.domain.repository.ChatRepository
import javax.inject.Inject

class SaveChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {

    sealed interface Result {
        object Success: Result
        data class Failure(val exception: Exception) : Result
    }

    suspend fun saveChat(chatTitle: String) : Result {
        return try {
            chatRepository.saveChat(chatTitle)
            Result.Success
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}