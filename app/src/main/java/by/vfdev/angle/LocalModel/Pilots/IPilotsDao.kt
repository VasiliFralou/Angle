package by.vfdev.angle.LocalModel.Pilots

import androidx.room.Dao
import androidx.room.Query
import by.vfdev.angle.RemoteModel.Pilots

@Dao
interface IPilotsDao {

    @Query("SELECT * FROM Pilots")
    fun getAll(): List<Pilots>
}