package daniel.chatmodel.features.chat

import com.google.firebase.firestore.DocumentId
import java.util.*

data class MessageModel(
    @DocumentId val id: String = "",
    val text: String = "",
    val authorId: String = "",
    val sent: Date = Date()
)