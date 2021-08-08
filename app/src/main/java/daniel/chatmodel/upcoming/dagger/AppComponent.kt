package daniel.chatmodel.upcoming.dagger

import dagger.Component

@ExampleScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(daggerFragment: DaggerFragment)
    fun inject(storage: Storage)
}