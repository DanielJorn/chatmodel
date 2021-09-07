package daniel.chatmodel.features.chat

import com.google.firebase.firestore.DocumentId

data class ChatModel(
    @DocumentId val id: String = "",
    val chatTitle: String = "",
    val chatAvatarUrl: String = ""
)