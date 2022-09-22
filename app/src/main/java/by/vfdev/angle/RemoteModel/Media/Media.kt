package by.vfdev.angle.RemoteModel.Media

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media")
data class Media(
    val title: String,
    val tag: String,
    val date: String? = null,
    val nameChannel: String? = null,
    val link: String? = null,
    val image: String,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0)