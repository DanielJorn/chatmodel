package daniel.chatmodel.upcoming.dagger

import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(daggerFragment: DaggerFragment)
}