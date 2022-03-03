package by.vfdev.angle.RemoteModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "events")
data class EventsLocation(
    val city: String? = null,
    @PrimaryKey val day : String,
    val name: String? = null,
    val title: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null)

data class EventCallBack(
    @SerializedName("Events")
    var results: MutableList<EventsLocation>
)