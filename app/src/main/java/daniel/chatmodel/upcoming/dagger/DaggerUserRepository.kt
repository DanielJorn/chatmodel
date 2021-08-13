package daniel.chatmodel.upcoming.dagger

interface DaggerUserRepository {
    fun getUsers(): List<String>
}
