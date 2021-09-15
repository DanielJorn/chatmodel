package daniel.chatmodel.upcoming.room.domain.model

import daniel.chatmodel.upcoming.room.presentation.recycleradapter.DiffComparable

data class ChatPreview(
    override val id: String,
    val chatTitle: String
): DiffComparable
