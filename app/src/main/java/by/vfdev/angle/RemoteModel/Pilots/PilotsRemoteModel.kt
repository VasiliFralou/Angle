package by.vfdev.angle.RemoteModel.Pilots

import by.vfdev.angle.Api.ApiPilots
import javax.inject.Inject

class PilotsRemoteModel @Inject constructor() : IPilotsRemoteModel {

    private val apiPilots = ApiPilots.create()

    override suspend fun getPilotsRemoteData(): Result<PilotsCallBack> {
        return try {
            val pilots: PilotsCallBack = apiPilots.getPilots()
            Result.success(pilots)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}