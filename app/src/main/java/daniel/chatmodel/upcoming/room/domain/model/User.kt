package daniel.chatmodel.upcoming.room.domain.model

import daniel.chatmodel.upcoming.room.presentation.recycleradapter.DiffComparable

data class User(
    override val id: String,
    val name: String,
    val surname: String
) : DiffComparable