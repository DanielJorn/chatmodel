package daniel.chatmodel.features.chat.chatList

import daniel.chatmodel.R
import daniel.chatmodel.base.adapter.ItemModel

data class ChatPreviewModel(
    override val id: String = "",
    val chatTitle: String = "",
    val chatAvatarUrl: String = "",
    val lastMessageText: String = "",
    val onChatClicked: () -> Unit = {  }
) : ItemModel {
    override val layoutId = R.layout.item_chat_preview
}