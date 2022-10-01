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

    suspend fun getDataEvents():
            Result<MutableList<Events>> = withContext(Dispatchers.IO) {

        val eventsListEthernet = eventsRemoteModel.getEventsRemoteData()
        var eventsListLocal = eventsLocalModel.getAllEvents()

        if (eventsListLocal.isEmpty()) {
            eventsListLocal = updateDataEventsFromDB(eventsListEthernet)
        } else {
            launch {
                updateDataEventsFromDB(eventsListEthernet)
            }
        }
        return@withContext Result.success(eventsListLocal)
    }

    private suspend fun updateDataEventsFromDB(list: MutableList<Events>): MutableList<Events> {

        if (list.isNotEmpty()) {
            eventsLocalModel.insertEvents(list)
        }
        return list
    }
}