package daniel.chatmodel.upcoming.hilt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentHiltBinding

class HiltFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHiltBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_hilt, container, false)

        return binding.root
    }
}