package by.vfdev.angle.LocalModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.angle.RemoteModel.EventsLocation

@Dao
interface iEventsDao {

    @Insert
    suspend fun insertEvents(events: MutableList<EventsLocation>)

    @Query("SELECT * FROM Events")
    suspend fun getAllEvents(): MutableList<EventsLocation>
}