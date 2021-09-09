package daniel.chatmodel.upcoming.room.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentRoomBinding
import kotlinx.android.synthetic.main.fragment_room.*
import javax.inject.Inject

@AndroidEntryPoint
class RoomFragment: Fragment() {
    private val viewModel : RoomViewModel by viewModels()

    @Inject
    lateinit var adapter: RoomUserAdapter

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

        viewModel.userList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}