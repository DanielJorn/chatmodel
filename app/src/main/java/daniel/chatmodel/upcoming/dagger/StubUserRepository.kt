package daniel.chatmodel.upcoming.dagger

import retrofit2.Retrofit
import javax.inject.Inject

class StubUserRepository @Inject constructor(retrofit: Retrofit): DaggerUserRepository {
    override fun getUsers(): List<String> {
        return listOf(
            "user1",
            "user2",
            "user3",
            "user4",
        )
    }
}