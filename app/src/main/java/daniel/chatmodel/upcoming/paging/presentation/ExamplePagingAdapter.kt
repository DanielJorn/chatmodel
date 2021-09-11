package daniel.chatmodel.upcoming.paging.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import daniel.chatmodel.R
import daniel.chatmodel.upcoming.paging.data.PagingUser
import kotlinx.android.synthetic.main.item_paging_user.view.*

class ExamplePagingAdapter :
    PagingDataAdapter<PagingUser, ExamplePagingAdapter.UserViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paging_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView? = view.pagingUserName
        val surname: TextView? = view.pagingUserSurname

        fun bind(item: PagingUser?) {
            name?.text = item?.name
            surname?.text = item?.surname
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PagingUser>() {
        override fun areItemsTheSame(oldItem: PagingUser, newItem: PagingUser): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PagingUser, newItem: PagingUser): Boolean {
            return oldItem == newItem
        }
    }
}
