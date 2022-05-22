package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Pilots.PilotsLocalModel
import by.vfdev.angle.RemoteModel.Pilots.Pilots
import by.vfdev.angle.RemoteModel.Pilots.PilotsRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PilotsRepository @Inject constructor(
    private val pilotsRemoteModel: PilotsRemoteModel,
    private val pilotsLocalModel: PilotsLocalModel) {

    suspend fun getDataPilots(): MutableList<Pilots> = withContext(Dispatchers.IO) {

        var pilotsNews = pilotsRemoteModel.getPilotsRemoteData()

        if (pilotsNews.isNullOrEmpty()) {
            pilotsNews = pilotsLocalModel.getAllPilots()
        }  else {
            launch {
                updateDataPilotsFromDB()
            }
        }
        return@withContext pilotsNews
    }

    private suspend fun updateDataPilotsFromDB(): MutableList<Pilots> {

        val pilotsList = pilotsRemoteModel.getPilotsRemoteData()
        pilotsLocalModel.insertPilots(pilotsList)

        return pilotsList
    }
}