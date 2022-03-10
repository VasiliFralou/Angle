package by.vfdev.angle.RemoteModel.Gallery

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Gallery(
    @PrimaryKey val img: String)