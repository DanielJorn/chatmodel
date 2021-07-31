package daniel.chatmodel.upcoming.room

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "username")
    val name: String,
    @ColumnInfo(typeAffinity = TEXT)
    val surname: String,
)
