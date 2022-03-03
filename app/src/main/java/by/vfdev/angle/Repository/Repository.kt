package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.LocalModel
import by.vfdev.angle.RemoteModel.EventsLocation
import by.vfdev.angle.RemoteModel.RemoteModel
import javax.inject.Inject

class Repository @Inject constructor(
    val remoteModel: RemoteModel,
    val localModel: LocalModel) {

    suspend fun getData() : MutableList<EventsLocation> {
        var eventsList = localModel.getAllEvents()
        return if (eventsList.isEmpty()) {
            eventsList = remoteModel.getRemoteData()
            localModel.insertEvents(eventsList)
            eventsList
        } else {
            eventsList
        }
    }
}