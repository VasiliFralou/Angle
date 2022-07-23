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

    suspend fun getDataPilots():
            Result<MutableList<Pilots>> = withContext(Dispatchers.IO) {

        val pilotsListEthernet = pilotsRemoteModel.getPilotsRemoteData()
        var pilotsListLocal = pilotsLocalModel.getAllPilots()

        if (pilotsListLocal.isEmpty()) {
            pilotsListLocal = updateDataPilotsFromDB(pilotsListEthernet)
        } else {
            launch {
                updateDataPilotsFromDB(pilotsListEthernet)
            }
        }
        return@withContext Result.success(pilotsListLocal)
    }

    private suspend fun updateDataPilotsFromDB(list: MutableList<Pilots>): MutableList<Pilots> {

        if (list.isNotEmpty()) {
            pilotsLocalModel.insertPilots(list)
        }
        return list
    }
}