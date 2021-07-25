package daniel.chatmodel.features.chat.chatList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentChatListBinding
import kotlinx.android.synthetic.main.fragment_chat_list.*

class ChatListFragment: Fragment(){
    private lateinit var adapter: ChatListAdapter
    private val viewModel: ChatListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentChatListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_chat_list, container, false
        )
        return binding.root
    }

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