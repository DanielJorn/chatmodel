package daniel.chatmodel.features.chat.chatList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import daniel.chatmodel.databinding.ItemChatPreviewBinding

class ChatListAdapter
    : ListAdapter<ChatPreviewModel, ChatListAdapter.ChatPreviewViewHolder>(DiffCallback()) {

    var onItemClick: (ChatPreviewModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatPreviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemChatPreviewBinding =
            ItemChatPreviewBinding.inflate(inflater, parent, false)
        return ChatPreviewViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ChatPreviewViewHolder, position: Int) {
        val currentChat = currentList[position]
        holder.binding?.chat = currentChat
        holder.itemView.setOnClickListener { onItemClick(currentChat) }
    }

    class ChatPreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemChatPreviewBinding? = DataBindingUtil.bind(itemView)
    }

    companion object {
        @BindingAdapter("bind:chatImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, v: String?) {
            Picasso.get().load(v).into(imageView)
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<ChatPreviewModel>() {
    override fun areItemsTheSame(oldItem: ChatPreviewModel, newItem: ChatPreviewModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatPreviewModel, newItem: ChatPreviewModel): Boolean {
        return oldItem == newItem
    }
}