package by.vfdev.angle.RemoteModel.Pilots

interface IPilotsRemoteModel {
    suspend fun getPilotsRemoteData(): Result<PilotsCallBack>
}