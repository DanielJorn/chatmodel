package daniel.chatmodel

import android.app.Application
import androidx.room.Room
import daniel.chatmodel.upcoming.room.AppDatabase

class ChatApplication : Application(){
    //todo I provide public access to AppDatabase through static instance of Application class
    // clunky, but i didn't do any further research
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