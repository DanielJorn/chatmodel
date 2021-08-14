package daniel.chatmodel.upcoming.hilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent ::class)
interface DataSourceModule {
    @Binds
    fun dataSource(stubDataSource: StubDataSource): HiltDataSource

    companion object {
        @Provides
        fun providesRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://google.com")
                .build()
        }
    }
}