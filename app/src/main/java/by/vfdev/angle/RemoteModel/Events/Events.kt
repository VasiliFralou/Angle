package by.vfdev.angle.RemoteModel.Events

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Events(
    val city: String? = null,
    @PrimaryKey val day : String,
    val name: String? = null,
    val title: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null)
