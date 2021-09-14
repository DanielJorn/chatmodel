package daniel.chatmodel.upcoming.room.presentation.recycleradapter

import androidx.annotation.LayoutRes

data class RecyclerItem(
    val data: DiffComparable,
    @get:LayoutRes val layoutId: Int
)