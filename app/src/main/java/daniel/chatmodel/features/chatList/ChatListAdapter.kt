package daniel.chatmodel.features.chatList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import daniel.chatmodel.R
import daniel.chatmodel.base.firestore.CHATS
import daniel.chatmodel.base.firestore.MESSAGES
import daniel.chatmodel.base.firestore.SENT
import kotlinx.android.synthetic.main.item_chat_preview.view.*

class ChatListAdapter(private val chatList: ArrayList<ChatModel>) :
    RecyclerView.Adapter<ChatListAdapter.ChatPreviewViewHolder>() {

    private val database = Firebase.firestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatPreviewViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_chat_preview, parent, false)
        return ChatPreviewViewHolder(view)
    }

    override fun getItemCount() = chatList.size

    override fun onBindViewHolder(holder: ChatPreviewViewHolder, position: Int) {
        val currentChat = chatList[position]
        holder.chatTitle.text = currentChat.title

        val chatId = currentChat.id
        database.collection(CHATS)
            .document(chatId)
            .collection(MESSAGES)
            .orderBy(SENT, Query.Direction.DESCENDING)
            .limit(1)
            .addSnapshotListener { snapshot, e ->
                if (snapshot!!.documents.size == 0)
                    return@addSnapshotListener

                val lastMessageDocument = snapshot.documents[0]
                val message = lastMessageDocument.toObject(MessageModel::class.java)?.text
                holder.messagePreview.text = message
            }


        Picasso.get().load(currentChat.chatImage).into(holder.chatImage)
    }


    class ChatPreviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chatTitle: TextView = view.chat_preview_chat_title
        val messagePreview: TextView = view.chat_preview_message_preview
        val chatImage: ImageView = view.chat_preview_chat_image
    }
}
