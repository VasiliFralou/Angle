package by.vfdev.angle.RemoteModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pilots")
open class Pilots (
    @PrimaryKey var id: Int,
    var name: String,
    var birthday: String? = null,
    var city: String,
    var auto: String,
    var team: String? = null,
    var instagram: String,
    var description: String? = null,
    var photo: ByteArray? = null)