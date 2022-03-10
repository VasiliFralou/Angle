package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Events.EventsLocalModel
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.RemoteModel.Events.EventsCallBack
import by.vfdev.angle.RemoteModel.Events.IEventsRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventsRemoteModel: IEventsRemoteModel,
    private val eventsLocalModel: EventsLocalModel
) {

    suspend fun getDataEvents(): Result<MutableList<Events>> = withContext(Dispatchers.IO) {

        val eventsList = eventsLocalModel.getAllEvents()

        if (eventsList.isEmpty()) {
            saveDataEventFromNetwork()
        } else {
            launch {
                saveDataEventFromNetwork()
            }
        }

        return@withContext Result.success(eventsList)

    }

    private suspend fun saveDataEventFromNetwork(): Result<EventsCallBack> {
        val eventsList = eventsRemoteModel.getEventsRemoteData()
        eventsList.getOrNull()?.let {
            eventsLocalModel.insertEvents(it.results)
        }
        return eventsList
    }
}