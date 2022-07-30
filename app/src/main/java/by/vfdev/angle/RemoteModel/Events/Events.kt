package by.vfdev.angle.RemoteModel.Events

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Events(
    val date: String? = null,
    val eventScheme: String? = null,
    val grid: String? = null,
    var judgesTask: String? = null,
    var latitude: Double? = null,
    var listPilots: List<String> = arrayListOf(),
    var locationImg: String? = null,
    var longitude: Double? = null,
    var location: String? = null,
    var name: String? = null,
    var title: String? = null,
    var promo: String? = null,
    var results: String? = null,
    var ticket: String? = null,
    var qualification: String? = null,
    @PrimaryKey(autoGenerate = true) var primaryKey: Int = 0)