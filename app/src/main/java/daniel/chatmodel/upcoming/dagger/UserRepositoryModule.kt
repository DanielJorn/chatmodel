package daniel.chatmodel.upcoming.dagger

import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
interface UserRepositoryModule {

    @Binds
    fun userRepository(stubUserRepository: StubUserRepository): DaggerUserRepository
}