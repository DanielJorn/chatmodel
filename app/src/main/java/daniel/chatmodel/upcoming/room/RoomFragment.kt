package daniel.chatmodel.upcoming.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentChatListBinding
import daniel.chatmodel.databinding.FragmentRoomBinding
import kotlinx.android.synthetic.main.fragment_room.*

class RoomFragment: Fragment() {

    private val viewModel : RoomViewModel by viewModels()
    private val userList = listOf<User>()
    private val adapter by lazy { RoomUserAdapter(userList) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentRoomBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_room, container, false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        room_users_recyclerView.adapter = adapter
        viewModel.loadUserList()
        viewModel.userList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}