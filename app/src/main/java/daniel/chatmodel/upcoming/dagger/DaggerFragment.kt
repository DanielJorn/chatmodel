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
import javax.inject.Inject

class DaggerFragment : Fragment() {

    @Inject
    lateinit var viewModel: DaggerViewModel

    override fun onAttach(context: Context) {
        ChatApplication.instance.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, bundle: Bundle?): View {
        val binding: FragmentDaggerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dagger, parent, false)

        binding.viewModel = viewModel
        return binding.root
    }
}