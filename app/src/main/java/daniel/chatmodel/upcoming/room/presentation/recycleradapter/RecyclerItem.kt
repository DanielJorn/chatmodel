package daniel.chatmodel.upcoming.room.presentation.recycleradapter

import android.widget.AdapterView
import androidx.annotation.LayoutRes

sealed class Click

data class RecyclerItem(
    val data: DiffComparable,
    @get:LayoutRes val layoutId: Int,
    val clickListener: ItemClickListener
)