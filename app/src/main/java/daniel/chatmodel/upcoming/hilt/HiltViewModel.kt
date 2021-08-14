package daniel.chatmodel.upcoming.hilt

import javax.inject.Inject

class HiltViewModel @Inject constructor(dataSource: HiltDataSource) {
    val data = dataSource.getData()
}
