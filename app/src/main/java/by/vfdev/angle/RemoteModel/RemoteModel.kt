package by.vfdev.angle.RemoteModel

import java.lang.Exception
import javax.inject.Inject

class RemoteModel @Inject constructor() {

    val apiService = ApiService.create()

    suspend fun getRemoteData(): MutableList<EventsLocation> {
        return try {
            val events: MutableList<EventsLocation> = apiService.getEvents().results
            events
        } catch (e:Exception) {
            mutableListOf()
        }
    }
}