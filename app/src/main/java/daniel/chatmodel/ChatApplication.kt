package daniel.chatmodel

import android.app.Application
import androidx.room.Room
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import daniel.chatmodel.upcoming.dagger.AppComponent
import daniel.chatmodel.upcoming.dagger.DaggerAppComponent
import daniel.chatmodel.upcoming.room.AppDatabase

@HiltAndroidApp
class ChatApplication : Application(){
    val appComponent = DaggerAppComponent.create()

    lateinit var database: AppDatabase
    private set

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    companion object {
        lateinit var instance: ChatApplication
            private set
    }
}