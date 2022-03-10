package by.vfdev.angle.RemoteModel.Events

interface IEventsRemoteModel {
    suspend fun getEventsRemoteData() : Result<EventsCallBack>
}