package daniel.chatmodel.upcoming.paging.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import daniel.chatmodel.R
import kotlinx.android.synthetic.main.fragment_paging.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "PagingFragment"

class PagingFragment: Fragment(R.layout.fragment_paging) {
    private val viewModel: PagingViewModel by viewModels()
    private val pagingAdapter by lazy { ExamplePagingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAG", "onViewCreated: sp[dask")

        pagingRecyclerView.adapter = pagingAdapter

        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                Log.d(TAG, "onViewCreated: $it")
                pagingAdapter.submitData(it)
            }
        }
    }
}