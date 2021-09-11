package daniel.chatmodel.upcoming.paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import daniel.chatmodel.upcoming.paging.data.ExampleBackendService
import daniel.chatmodel.upcoming.paging.data.ExamplePagingSource
import kotlinx.coroutines.launch

class PagingViewModel: ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 10)
    ) {
       ExamplePagingSource(ExampleBackendService(), "queryExample")
    }.flow
        .cachedIn(viewModelScope)
}