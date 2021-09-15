package daniel.chatmodel.upcoming.room.presentation.recycleradapter

import daniel.chatmodel.upcoming.room.domain.model.ChatPreview

interface RoomChatPreviewClickListener: ItemClickListener {
    fun onChatPreviewClicked(chatPreview: ChatPreview)
}