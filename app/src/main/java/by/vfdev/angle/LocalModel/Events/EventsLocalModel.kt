package by.vfdev.angle.LocalModel.Events

import android.content.Context
import by.vfdev.angle.RemoteModel.Events.Events
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EventsLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = EventsDatabase.getDataBase(context).eventsDao()

    suspend fun insertEvents(events: MutableList<Events>) {
        database.deleteAllEvents()
        database.insertEvents(events)
    }

    suspend fun getAllEvents() : MutableList<Events> {
        return database.getAllEvents()
    }
}