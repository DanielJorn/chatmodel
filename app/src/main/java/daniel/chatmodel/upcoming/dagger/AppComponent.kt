package daniel.chatmodel.upcoming.dagger

import dagger.Component
import daniel.chatmodel.MainActivity

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(daggerFragment: DaggerFragment)
}