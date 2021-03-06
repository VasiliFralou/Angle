package by.vfdev.angle.RemoteModel.Pilots

import by.vfdev.angle.Api.ApiPilots
import javax.inject.Inject

class PilotsRemoteModel @Inject constructor() {

    private val apiPilots = ApiPilots.create()

    suspend fun getPilotsRemoteData(): MutableList<Pilots> {
        return try {
            val pilots: MutableList<Pilots> = apiPilots.getPilots().results
            pilots
        } catch (e: Exception) {
            mutableListOf()
        }
    }
}