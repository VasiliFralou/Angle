package by.vfdev.angle.RemoteModel.News

interface INewsRemoteModel {
    suspend fun getNewsRemoteData(): Result<NewsCallBack>
}