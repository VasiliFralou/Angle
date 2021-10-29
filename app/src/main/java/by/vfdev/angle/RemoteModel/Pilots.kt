package by.vfdev.angle.RemoteModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pilots")
open class Pilots (
    @PrimaryKey var id: Int,
    var name: String,
    var city: String,
    var photo: ByteArray? = null,
    var birthday: String? = "-",
    var team: String? = "-",
    var instagram: String,
    var description: String? = "-",
    var auto: String,
    var photoAuto: ByteArray? = null)