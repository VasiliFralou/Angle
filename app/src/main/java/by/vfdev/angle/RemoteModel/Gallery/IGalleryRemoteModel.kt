package by.vfdev.angle.RemoteModel.Gallery

interface IGalleryRemoteModel {
    suspend fun getGalleryRemoteData() : Result<GalleryCallBack>
}