package daniel.chatmodel.upcoming.dagger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentDaggerBinding
import javax.inject.Inject

class DaggerFragment: Fragment() {

    @Inject lateinit var exampleInject: ExampleInject

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDaggerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dagger, container, false
        )

        DaggerAppComponent.create().inject(this)
        binding.exampleInject = exampleInject
        return binding.root
    }
}