package daniel.chatmodel

import android.app.Application
import androidx.room.Room
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import daniel.chatmodel.upcoming.room.data.database.UserDatabase

@HiltAndroidApp
class ChatApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}