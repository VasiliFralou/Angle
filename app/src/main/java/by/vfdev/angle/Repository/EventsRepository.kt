package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Events.EventsLocalModel
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.RemoteModel.Events.EventsRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventsRemoteModel: EventsRemoteModel,
    private val eventsLocalModel: EventsLocalModel) {

    suspend fun getDataEvents(): MutableList<Events> = withContext(Dispatchers.IO) {

        var eventsList = eventsRemoteModel.getEventsRemoteData()

        if (eventsList.isNullOrEmpty()) {
            eventsList = eventsLocalModel.getAllEvents()
        }  else {
            launch {
                updateDataEventsFromDB()
            }
        }
        return@withContext eventsList
    }

    private suspend fun updateDataEventsFromDB(): MutableList<Events> {

        val eventsList = eventsRemoteModel.getEventsRemoteData()
        eventsLocalModel.insertEvents(eventsList)

        return eventsList
    }
}