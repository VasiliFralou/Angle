package by.vfdev.angle.LocalModel.Pilots

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.angle.RemoteModel.Pilots.Pilots

@Dao
interface IPilotsDao {

    @Insert
    suspend fun insertPilots(pilots: MutableList<Pilots>)

    @Query("SELECT * FROM Pilots")
    fun getAllPilots(): MutableList<Pilots>

    @Query("DELETE FROM Pilots")
    suspend fun deleteAllPilots()
}