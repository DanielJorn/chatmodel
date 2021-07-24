package daniel.chatmodel.features.chatList

import com.google.firebase.firestore.DocumentId

data class MessageModel(
    @DocumentId val id: String = "",
    val text: String = ""
)