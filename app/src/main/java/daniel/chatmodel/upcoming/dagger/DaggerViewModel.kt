package daniel.chatmodel.upcoming.dagger

import daniel.chatmodel.features.chat.chatList.ChatListRepository
import javax.inject.Inject

class DaggerViewModel @Inject constructor(private val userRepository: DaggerUserRepository) {
    val text = "Hello"
    val users = userRepository.getUsers()
}