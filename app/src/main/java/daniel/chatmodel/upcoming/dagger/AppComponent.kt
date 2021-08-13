package daniel.chatmodel.upcoming.dagger

import dagger.Component

@Component(modules = [UserRepositoryModule::class])
interface AppComponent {
    fun inject(daggerFragment: DaggerFragment)
}