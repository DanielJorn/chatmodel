package daniel.chatmodel.upcoming.paging.data

import android.util.Log

private const val TAG = "ExampleBackendService"

class ExampleBackendService {
    fun searchUsers(query: String, nextPageNumber: Int): Response {
        Log.d(TAG, "searchUsers: ")
        return Response(usersFromPage(query, nextPageNumber), nextPageNumber + 1)
    }

    private fun usersFromPage(query: String, nextPageNumber: Int): List<PagingUser> {
        return List(10) {
            PagingUser("$query$nextPageNumber", "${nextPageNumber}$it", "nextPageNumber:${nextPageNumber}")
        }
    }

}
