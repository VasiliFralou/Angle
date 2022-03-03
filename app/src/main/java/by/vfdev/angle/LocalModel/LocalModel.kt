package by.vfdev.angle.LocalModel

import android.content.Context
import androidx.room.Room
import by.vfdev.angle.RemoteModel.EventsLocation
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database: EventsDatabase = Room.databaseBuilder(
        context,
        EventsDatabase::class.java, "events_db"
    ).build()

    suspend fun insertEvents(events: MutableList<EventsLocation>) {
        database.eventDao().insertEvents(events)
    }

    suspend fun getAllEvents() : MutableList<EventsLocation> {
        return database.eventDao().getAllEvents()
    }
}