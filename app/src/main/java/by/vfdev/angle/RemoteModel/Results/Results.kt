package by.vfdev.angle.RemoteModel.Results

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class Results(
    val name: String,
    val pointsQual1: Int? = null,
    val pointsRnd1: Int? = null,
    val pointsQual2: Int? = null,
    val pointsRnd2: Int? = null,
    val pointsQual3: Int? = null,
    val pointsRnd3: Int? = null,
    val pointsQual4: Int? = null,
    val pointsRnd4: Int? = null,
    val pointsQual5: Int? = null,
    val pointsRnd5: Int? = null,
    val pointResult: Int? = null,
    var expandable: Boolean = false,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0)