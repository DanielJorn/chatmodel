package daniel.chatmodel.upcoming.dagger

import dagger.Component

@Component
interface AppComponent {
    fun inject(daggerFragment: DaggerFragment)
}