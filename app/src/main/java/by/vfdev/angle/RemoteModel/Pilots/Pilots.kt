package by.vfdev.angle.RemoteModel.Pilots

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pilots")
data class Pilots (
    var name: String,
    var city: String,
    var photo: String? = null,
    var birthday: String? = "-",
    var team: String? = "-",
    var instagram: String = "-",
    var description: String? = "-",
    var auto: String,
    var photoAuto: String,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0)