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
import javax.inject.Named

class DaggerFragment: Fragment() {

    @Inject @Named("first") lateinit var exampleInject1: ExampleInject
    @Inject @Named("second") lateinit var exampleInject2: ExampleInject

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDaggerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dagger, container, false
        )

        DaggerAppComponent.create().inject(this)
        binding.exampleInject1 = exampleInject1
        binding.exampleInject2 = exampleInject2
        return binding.root
    }
}