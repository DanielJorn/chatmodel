package daniel.chatmodel.features.chat.chatList

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import daniel.chatmodel.R
import daniel.chatmodel.base.Failure
import daniel.chatmodel.base.Loading
import daniel.chatmodel.base.Success
import daniel.chatmodel.base.adapter.ItemListAdapter
import kotlinx.android.synthetic.main.fragment_chat_list.*

private const val TAG = "ChatListFragment"

@AndroidEntryPoint
class ChatListFragment : Fragment() {
    private val viewModel: ChatListViewModel by viewModels()
    private val adapter: ItemListAdapter by lazy { ItemListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_chat_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeChatListUpdates()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        chatList_chats_recyclerView.adapter = adapter

        val itemDecor = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
        chatList_chats_recyclerView.addItemDecoration(itemDecor)
    }

    private fun observeChatListUpdates() {
        viewModel.chatList.observe(viewLifecycleOwner) {
            when (it) {
                is Loading -> toast(getString(R.string.toast_loading_data))
                is Failure -> toast(getString(R.string.toast_something_went_wrong))
                is Success -> adapter.submitList(it.data)
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, LENGTH_SHORT).show()
    }
}