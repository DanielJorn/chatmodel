package daniel.chatmodel.upcoming.hilt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentHiltBinding
import javax.inject.Inject

@AndroidEntryPoint
class HiltFragment : Fragment() {

    @Inject
    lateinit var viewModel: HiltViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHiltBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_hilt, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}