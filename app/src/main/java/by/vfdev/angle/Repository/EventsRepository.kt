package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Events.EventsLocalModel
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.RemoteModel.Events.EventsRemoteModel
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventsRemoteModel: EventsRemoteModel,
    private val eventsLocalModel: EventsLocalModel) {

    suspend fun getDataEvents(): MutableList<Events> {

        var eventsList = eventsLocalModel.getAllEvents()

        return if (eventsList.isEmpty()) {
            eventsList = eventsRemoteModel.getEventsRemoteData()
            eventsLocalModel.insertEvents(eventsList)
            eventsList
        } else {
            eventsList
        }
    }
}