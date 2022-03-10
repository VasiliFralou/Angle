package by.vfdev.angle.RemoteModel.Events

import by.vfdev.angle.Api.ApiEvents
import javax.inject.Inject

class EventsRemoteModel @Inject constructor() : IEventsRemoteModel {

    private val apiEvents = ApiEvents.create()

    override suspend fun getEventsRemoteData(): Result<EventsCallBack> {
        return try {
            val events: EventsCallBack = apiEvents.getEvents()
            Result.success(events)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}