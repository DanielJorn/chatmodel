package daniel.chatmodel.features.chatList

import com.google.firebase.firestore.DocumentId

data class FirebaseChatModel(
    @DocumentId val id: String = "",
    val title: String = "",
    val chatImage: String = ""
)