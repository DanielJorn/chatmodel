package daniel.chatmodel.upcoming.dagger

import dagger.Component
import daniel.chatmodel.MainActivity

@Component
interface AppComponent {
    fun inject(daggerFragment: DaggerFragment)
}