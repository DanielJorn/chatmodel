package daniel.chatmodel.upcoming.paging.data

data class Response(
    val users: List<PagingUser>,
    val nextPageNumber: Int
)
