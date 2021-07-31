package daniel.chatmodel

import android.app.Application
import androidx.room.Room
import daniel.chatmodel.upcoming.room.AppDatabase

class ChatApplication : Application(){
    companion object {
        lateinit var instance: ChatApplication
    }

    lateinit var database: AppDatabase
    private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }
}