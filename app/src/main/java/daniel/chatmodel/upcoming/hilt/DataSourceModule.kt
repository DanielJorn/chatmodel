package daniel.chatmodel.upcoming.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent ::class)
interface DataSourceModule {
    @Binds
    fun dataSource(stubDataSource: StubDataSource): HiltDataSource
}