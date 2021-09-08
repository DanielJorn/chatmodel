package daniel.chatmodel.base.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun bindItemViewModels(recyclerView: RecyclerView, itemViewModels: List<ItemModel>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.submitList(itemViewModels)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): ItemListAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is ItemListAdapter) {
        recyclerView.adapter as ItemListAdapter
    } else {
        val itemListAdapter = ItemListAdapter()
        recyclerView.adapter = itemListAdapter
        itemListAdapter
    }
}

@BindingAdapter("itemDecoration")
fun setItemDecoration(recyclerView: RecyclerView, drawableOrientation: Int){
    val itemDecor = DividerItemDecoration(recyclerView.context, drawableOrientation)
    recyclerView.addItemDecoration(itemDecor)
}