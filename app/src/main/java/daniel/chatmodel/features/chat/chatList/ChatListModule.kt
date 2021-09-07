package daniel.chatmodel.features.chat.chatList

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ChatListModule {
    @Binds
    fun bindChatListRepository(firebaseChatListRepository: FirebaseChatListRepository): ChatListRepository

    @Binds
    fun bindMessageRepository(firebaseMessageRepository: FirebaseMessageRepository): MessageRepository
}