package daniel.chatmodel.upcoming.room

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import daniel.chatmodel.upcoming.room.data.ChatPreviewRepositoryImpl
import daniel.chatmodel.upcoming.room.data.UserRepositoryImpl
import daniel.chatmodel.upcoming.room.data.database.UserDao
import daniel.chatmodel.upcoming.room.data.database.UserDatabase
import daniel.chatmodel.upcoming.room.domain.repository.ChatPreviewRepository
import daniel.chatmodel.upcoming.room.domain.repository.UserRepository
import daniel.chatmodel.upcoming.room.presentation.RoomFragment
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RoomFeatureModule {

    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindChatPreviewRepository(userRepositoryImpl: ChatPreviewRepositoryImpl): ChatPreviewRepository

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
    }
}