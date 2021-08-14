package daniel.chatmodel.upcoming.hilt

import retrofit2.Retrofit
import javax.inject.Inject

class StubDataSource @Inject constructor(retrofit: Retrofit) : HiltDataSource {
    override fun getData(): String {
        return "stub data source"
    }
}