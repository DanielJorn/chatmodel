package daniel.chatmodel.base.adapter

import androidx.annotation.LayoutRes

interface ItemModel {
    val id: String
    @get:LayoutRes
    val layoutId: Int

    fun areContentsTheSame(other: ItemModel) = this == other
}
