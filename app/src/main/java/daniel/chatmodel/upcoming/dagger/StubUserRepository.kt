package daniel.chatmodel.upcoming.dagger

import javax.inject.Inject

class StubUserRepository @Inject constructor(): DaggerUserRepository {
    override fun getUsers(): List<String> {
        return listOf(
            "user1",
            "user2",
            "user3",
            "user4",
        )
    }
}