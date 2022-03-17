package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Pilots.PilotsLocalModel
import by.vfdev.angle.RemoteModel.Pilots.Pilots
import by.vfdev.angle.RemoteModel.Pilots.PilotsCallBack
import by.vfdev.angle.RemoteModel.Pilots.PilotsRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PilotsRepository @Inject constructor(
    private val pilotsRemoteModel: PilotsRemoteModel,
    private val pilotsLocalModel: PilotsLocalModel) {

    suspend fun getDataPilots(): Result<MutableList<Pilots>> = withContext(Dispatchers.IO) {

        val pilotsList = pilotsLocalModel.getAllPilots()

        if (pilotsList.isEmpty()) {
            saveDataPilotsFromNetwork()
        } else {
            launch {
                saveDataPilotsFromNetwork()
            }
        }
        return@withContext Result.success(pilotsList)
    }

    private suspend fun saveDataPilotsFromNetwork(): Result<PilotsCallBack> {
        val pilotsList = pilotsRemoteModel.getPilotsRemoteData()
        pilotsList.getOrNull()?.let {
            pilotsLocalModel.insertPilots(it.results)
        }
        return pilotsList
    }
}