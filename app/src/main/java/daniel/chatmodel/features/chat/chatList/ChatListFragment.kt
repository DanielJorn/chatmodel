package daniel.chatmodel.features.chat.chatList

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import daniel.chatmodel.R
import daniel.chatmodel.base.Failure
import daniel.chatmodel.base.Loading
import daniel.chatmodel.base.Success
import daniel.chatmodel.base.adapter.ItemListAdapter
import daniel.chatmodel.databinding.FragmentChatListBinding
import kotlinx.android.synthetic.main.fragment_chat_list.*

private const val TAG = "ChatListFragment"

@AndroidEntryPoint
class ChatListFragment : Fragment() {
    private val viewModel: ChatListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentChatListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_chat_list,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}