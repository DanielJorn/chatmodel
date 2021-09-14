package daniel.chatmodel.upcoming.room.presentation.recycleradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject
import daniel.chatmodel.BR

class DynamicAdapter @Inject constructor() :
    ListAdapter<RecyclerItem, DynamicAdapter.ItemViewHolder>(DiffCallback1()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recyclerItem: RecyclerItem) {
            binding.setVariable(BR.item, recyclerItem.data)
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).layoutId
}

private class DiffCallback1 : DiffUtil.ItemCallback<RecyclerItem>() {
    override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
        return oldItem.data.areItemsTheSame(newItem.data)
    }

    override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
        return oldItem.data.areContentsTheSame(newItem.data)
    }
}