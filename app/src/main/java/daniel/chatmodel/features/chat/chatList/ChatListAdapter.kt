package daniel.chatmodel.features.chat.chatList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import daniel.chatmodel.databinding.ItemChatPreviewBinding
import kotlinx.android.synthetic.main.item_chat_preview.view.*

class ChatListAdapter(private val chatList: ArrayList<ChatModel>) :
    RecyclerView.Adapter<ChatListAdapter.ChatPreviewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatPreviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemChatPreviewBinding = ItemChatPreviewBinding.inflate(inflater, parent, false)
        return ChatPreviewViewHolder(binding.root)
    }

    override fun getItemCount() = chatList.size

    override fun onBindViewHolder(holder: ChatPreviewViewHolder, position: Int) {
        val currentChat = chatList[position]
        holder.binding?.chat = currentChat
    }


    class ChatPreviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemChatPreviewBinding? = DataBindingUtil.bind(view)
    }

    companion object {

        @BindingAdapter("bind:chatImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, v: String?) {
            Picasso.get().load(v).into(imageView)
        }
    }
}
