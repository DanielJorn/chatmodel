package daniel.chatmodel.upcoming.dagger

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import daniel.chatmodel.ChatApplication
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentDaggerBinding
import kotlinx.android.synthetic.main.fragment_dagger.*
import kotlinx.android.synthetic.main.fragment_dagger.view.*
import javax.inject.Inject
import javax.inject.Named

class DaggerFragment : Fragment() {

    @Inject
    lateinit var viewModel: DaggerViewModel

    override fun onAttach(context: Context) {
        ChatApplication.instance.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDaggerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dagger, container, false
        )
        binding.viewModel = viewModel

        return binding.root
    }
}