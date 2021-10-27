package by.vfdev.angle.LocalModel

import androidx.room.Dao
import androidx.room.Query
import by.vfdev.angle.RemoteModel.Pilots

@Dao
interface iPilotsDao {
    @Query("SELECT * FROM Pilots")
    fun getAll(): List<Pilots>
}