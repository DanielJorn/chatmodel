package daniel.chatmodel.features.chat.chatList

import daniel.chatmodel.base.State
import kotlinx.coroutines.flow.Flow

interface ChatListRepository {
    fun loadChatList(): Flow<State<List<ChatPreviewModel>>>

    fun onCleared()
}

