package daniel.chatmodel.upcoming.hilt

import javax.inject.Inject

class StubDataSource @Inject constructor() : HiltDataSource {
    override fun getData(): String {
        return "stub data source"
    }
}