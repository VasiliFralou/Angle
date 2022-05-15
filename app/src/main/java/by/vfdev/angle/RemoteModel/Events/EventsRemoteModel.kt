package by.vfdev.angle.RemoteModel.Events

import by.vfdev.angle.Api.ApiEvents
import javax.inject.Inject

class EventsRemoteModel @Inject constructor() {

    private val apiEvents = ApiEvents.create()

    suspend fun getEventsRemoteData(): MutableList<Events> {
        return try {
            val events = apiEvents.getEvents().results
            events
        } catch (e: Exception) {
            mutableListOf()
        }
    }
}