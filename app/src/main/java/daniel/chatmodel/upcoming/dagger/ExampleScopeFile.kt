package daniel.chatmodel.upcoming.dagger

import javax.inject.Inject

var staticCount = 0

@ExampleScope
class ScopedClass @Inject constructor() {
    val count = ++staticCount
}

class NotScopedClass @Inject constructor() {
    val count = ++staticCount
}