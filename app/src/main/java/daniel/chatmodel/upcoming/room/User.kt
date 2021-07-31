package daniel.chatmodel.upcoming.room

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey

data class User(
    @PrimaryKey
    val id: String,
    val name: String,
    val surname: String,
)
