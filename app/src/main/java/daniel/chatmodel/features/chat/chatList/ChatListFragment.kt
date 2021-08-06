package daniel.chatmodel.features.chat.chatList

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import daniel.chatmodel.R
import daniel.chatmodel.base.Success
import kotlinx.android.synthetic.main.fragment_chat_list.*
private const val TAG = "ChatListFragment"

class ChatListFragment : Fragment() {
    private val viewModel: ChatListViewModel by viewModels()
    private val adapter: ChatListAdapter by lazy { ChatListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_chat_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        viewModel.chatList.observe(viewLifecycleOwner) {
            when (it){
                is Success -> adapter.submitList(it.data)
                else -> Log.d(TAG, "onViewCreated: lazy")
            }
        }
    }


    private fun setupUI() {
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        chatList_chats_recyclerView.adapter = adapter
        adapter.onItemClick = {
            Log.d(TAG, "setupRecyclerView: $it")
        }

        val itemDecor = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
        chatList_chats_recyclerView.addItemDecoration(itemDecor)
    }
}