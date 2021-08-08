package daniel.chatmodel.upcoming.dagger

import javax.inject.Inject

class Storage {
    @Inject
    lateinit var scoped: ScopedClass
    @Inject
    lateinit var notScoped: NotScopedClass
}