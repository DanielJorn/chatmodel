package daniel.chatmodel.upcoming.dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
class AppModule {
    @Provides
    fun providesExampleInject(): ExampleInject {
        return ExampleInject("example with constructor")
    }
}