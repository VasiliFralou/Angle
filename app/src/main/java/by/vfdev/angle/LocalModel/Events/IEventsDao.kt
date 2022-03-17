package by.vfdev.angle.LocalModel.Events

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.angle.RemoteModel.Events.Events

@Dao
interface IEventsDao {

    @Insert
    suspend fun insertEvents(events: MutableList<Events>)

    @Query("SELECT * FROM Events")
    suspend fun getAllEvents(): MutableList<Events>

    @Query("DELETE FROM Events")
    suspend fun deleteAllEvents()
}