package daniel.chatmodel.upcoming.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import daniel.chatmodel.databinding.ItemRoomUserBinding

class RoomUserAdapter : ListAdapter<User, RoomUserAdapter.ItemViewHolder>(DiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRoomUserBinding = ItemRoomUserBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.binding?.user = currentItem
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemRoomUserBinding? = DataBindingUtil.bind(itemView)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}