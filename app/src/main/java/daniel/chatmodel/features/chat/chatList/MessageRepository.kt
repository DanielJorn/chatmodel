package daniel.chatmodel.features.chat.chatList

import daniel.chatmodel.base.State
import daniel.chatmodel.features.chat.MessageModel
import kotlinx.coroutines.flow.Flow

private const val TAG = "MessageRepository"

interface MessageRepository {
    fun listenLastMessage(chatId: String): Flow<State<MessageModel>>
}