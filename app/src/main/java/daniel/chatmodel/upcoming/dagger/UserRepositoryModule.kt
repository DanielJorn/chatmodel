package daniel.chatmodel.upcoming.dagger

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import retrofit2.Retrofit

@Module
@DisableInstallInCheck
interface UserRepositoryModule {

    @Binds
    fun userRepository(stubUserRepository: StubUserRepository): DaggerUserRepository

    companion object {
        @Provides
        fun providesRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://google.com")
                .build()
        }
    }
}