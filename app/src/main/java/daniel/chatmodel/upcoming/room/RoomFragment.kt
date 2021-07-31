package daniel.chatmodel.upcoming.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentChatListBinding

class RoomFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentChatListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_chat_room, container, false
        )
        return binding.root
    }
}