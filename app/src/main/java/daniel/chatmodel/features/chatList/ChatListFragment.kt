package daniel.chatmodel.features.chatList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import daniel.chatmodel.R
import kotlinx.android.synthetic.main.fragment_chat_list.*

class ChatListFragment: Fragment(R.layout.fragment_chat_list){
    private lateinit var adapter: ChatListAdapter
    private val viewModel: ChatListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateLiveData.observe(viewLifecycleOwner) { onChatListUpdated() }

        adapter = ChatListAdapter(viewModel.chatList)
        viewModel.updateChatList()
        rv_chat_list.adapter=adapter
    }

    private fun onChatListUpdated() {
        adapter.notifyDataSetChanged()
    }
}