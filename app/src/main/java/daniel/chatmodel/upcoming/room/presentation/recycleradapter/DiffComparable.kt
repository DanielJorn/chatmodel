package daniel.chatmodel.upcoming.room.presentation.recycleradapter

interface DiffComparable {
    val id: String

    fun areItemsTheSame(newItem: DiffComparable) = id == newItem.id
    fun areContentsTheSame(newItem: DiffComparable) = this == newItem
}
