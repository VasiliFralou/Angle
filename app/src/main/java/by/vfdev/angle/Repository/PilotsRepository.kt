package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Pilots.PilotsLocalModel
import by.vfdev.angle.RemoteModel.Pilots.Pilots
import by.vfdev.angle.RemoteModel.Pilots.PilotsRemoteModel
import javax.inject.Inject

class PilotsRepository @Inject constructor(
    private val pilotsRemoteModel: PilotsRemoteModel,
    private val pilotsLocalModel: PilotsLocalModel) {

    suspend fun getDataPilots(): MutableList<Pilots> {

        var pilotsList = pilotsLocalModel.getAllPilots()

        return if (pilotsList.isEmpty()) {
            pilotsList = pilotsRemoteModel.getPilotsRemoteData()
            pilotsLocalModel.insertPilots(pilotsList)
            pilotsList
        } else {
            pilotsList
        }
    }
}