package by.vfdev.angle.RemoteModel.News

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    val title: String? = null,
    val description: String? = null,
    val date: String? = null,
    val source: String? = null,
    val urlImg: String? = null,
    val urlPost: String,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0)