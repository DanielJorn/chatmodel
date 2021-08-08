package daniel.chatmodel.upcoming.dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Named

@Module
@DisableInstallInCheck
class AppModule {
    @Provides
    @Named("first")
    fun providesFirstExampleInject(): ExampleInject {
        return ExampleInject("first")
    }

    @Provides
    @Named("second")
    fun providesSecondExampleInject(): ExampleInject {
        return ExampleInject("second")
    }
}