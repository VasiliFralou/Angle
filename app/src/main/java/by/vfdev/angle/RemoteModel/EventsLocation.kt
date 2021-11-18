package by.vfdev.angle.RemoteModel

import com.google.firebase.database.Exclude

data class EventsLocation(
    val city: String? = null,
    @Exclude val day : String? = null,
    val name: String? = null,
    val title: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null) {
}