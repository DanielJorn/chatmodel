package daniel.chatmodel.features.chat

import com.google.firebase.firestore.DocumentId

data class ChatModel(
    @DocumentId val id: String = "",
    val title: String = "",
    val chatImage: String = ""
)