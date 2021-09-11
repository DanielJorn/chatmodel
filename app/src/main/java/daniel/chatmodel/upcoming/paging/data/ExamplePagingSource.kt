package daniel.chatmodel.upcoming.paging.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

private const val TAG = "ExamplePagingSource"

class ExamplePagingSource(
    val backend: ExampleBackendService,
    val query: String
) : PagingSource<Int, PagingUser>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PagingUser> {
        Log.d(TAG, "load: ")
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = backend.searchUsers(query, nextPageNumber)
            Log.d(TAG, "load: $response")
            LoadResult.Page(
                data = response.users,
                prevKey = null, // Only paging forward.
                nextKey = response.nextPageNumber
            )
        } catch (e: Exception) {
            Log.d(TAG, "load: error")
            LoadResult.Error(e)
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PagingUser>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}