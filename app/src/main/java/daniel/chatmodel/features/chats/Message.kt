package daniel.chatmodel.features.chats

import com.google.firebase.firestore.DocumentId

data class Message(
    @DocumentId val id: String = "",
    val text: String = ""
)