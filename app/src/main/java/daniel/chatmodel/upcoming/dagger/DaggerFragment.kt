package daniel.chatmodel.upcoming.dagger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentDaggerBinding
import kotlinx.android.synthetic.main.fragment_dagger.*
import kotlinx.android.synthetic.main.fragment_dagger.view.*
import javax.inject.Inject
import javax.inject.Named

class DaggerFragment: Fragment() {

    private lateinit var appComponent: AppComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDaggerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dagger, container, false
        )
        appComponent = DaggerAppComponent.create()
        binding.root.dagger_testBtn.setOnClickListener {
            val storage = Storage()
            appComponent.inject(storage)

            binding.scoped =    storage.scoped
            binding.notScoped = storage.notScoped
        }

        return binding.root
    }
}