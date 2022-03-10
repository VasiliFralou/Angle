package by.vfdev.angle.LocalModel.Events

import android.content.Context
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.RemoteModel.Events.EventsCallBack
import by.vfdev.angle.RemoteModel.Events.IEventsRemoteModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EventsLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = EventsDatabase.getDataBase(context).eventDao()

    suspend fun insertEvents(events: MutableList<Events>) {
        database.deleteAllEvents()
        database.insertEvents(events)
    }

    suspend fun getAllEvents() : MutableList<Events> {
        return database.getAllEvents()
    }
}