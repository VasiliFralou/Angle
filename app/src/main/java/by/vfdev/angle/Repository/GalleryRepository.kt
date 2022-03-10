package by.vfdev.angle.Repository

import by.vfdev.angle.RemoteModel.Gallery.GalleryCallBack
import by.vfdev.angle.RemoteModel.Gallery.IGalleryRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val galleryRemoteModel: IGalleryRemoteModel
) {

    suspend fun getDataGallery(): Result<GalleryCallBack> = withContext(Dispatchers.IO) {

        return@withContext galleryRemoteModel.getGalleryRemoteData()
    }
}