package daniel.chatmodel.features.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import daniel.chatmodel.R
import kotlinx.android.synthetic.main.item_chat_preview.view.*

class ChatPreviewAdapter(private val chatList: ArrayList<ChatPreviewModel>) :
    RecyclerView.Adapter<ChatPreviewAdapter.ChatPreviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatPreviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_preview, parent, false)
        return ChatPreviewViewHolder(view)
    }

    override fun getItemCount() = chatList.size

    override fun onBindViewHolder(holder: ChatPreviewViewHolder, position: Int) {
        val currentChat = chatList[position]
        holder.chatTitle.text = currentChat.title
        holder.messagePreview.text = currentChat.message
    }

    class ChatPreviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chatTitle: TextView = view.chat_preview_chat_title
        val messagePreview: TextView = view.chat_preview_message_preview

    }
}
