package daniel.chatmodel.upcoming.room

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import daniel.chatmodel.upcoming.room.data.ChatRepositoryImpl
import daniel.chatmodel.upcoming.room.data.UserRepositoryImpl
import daniel.chatmodel.upcoming.room.data.database.ChatDao
import daniel.chatmodel.upcoming.room.data.database.ChatDatabase
import daniel.chatmodel.upcoming.room.data.database.UserDao
import daniel.chatmodel.upcoming.room.data.database.UserDatabase
import daniel.chatmodel.upcoming.room.domain.repository.ChatRepository
import daniel.chatmodel.upcoming.room.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RoomFeatureModule {

    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindChatPreviewRepository(userRepositoryImpl: ChatRepositoryImpl): ChatRepository

    companion object {
        @Provides
        fun providesUserDao(userDatabase: UserDatabase): UserDao {
            return userDatabase.userDao()
        }

        @Provides
        @Singleton
        fun providesUserDatabase(@ApplicationContext appContext: Context): UserDatabase {
            return Room.databaseBuilder(
                appContext,
                UserDatabase::class.java,
                "userDatabase"
            ).build()
        }

        @Provides
        fun providesChatDao(chatDatabase: ChatDatabase): ChatDao {
            return chatDatabase.chatDao()
        }

        @Provides
        @Singleton
        fun providesChatDatabase(@ApplicationContext appContext: Context): ChatDatabase {
            return Room.databaseBuilder(
                appContext,
                ChatDatabase::class.java,
                "chatDatabase"
            ).build()
        }
    }
}