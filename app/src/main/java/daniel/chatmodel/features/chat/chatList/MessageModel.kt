package daniel.chatmodel.features.chat.chatList

import com.google.firebase.firestore.DocumentId

data class MessageModel(
    @DocumentId val id: String = "",
    val text: String = ""
)