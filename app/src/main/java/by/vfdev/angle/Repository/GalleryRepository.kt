package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Gallery.GalleryLocalModel
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import by.vfdev.angle.RemoteModel.Gallery.GalleryCallBack
import by.vfdev.angle.RemoteModel.Gallery.IGalleryRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val galleryRemoteModel: IGalleryRemoteModel,
    private val galleryLocalModel: GalleryLocalModel
) {

    suspend fun getDataGallery(): Result<MutableList<Gallery>> = withContext(Dispatchers.IO) {

        val galleryList = galleryLocalModel.getAllGallery()

        if (galleryList.isEmpty()) {
            saveDataGalleryFromNetwork()
        } else {
            launch {
                saveDataGalleryFromNetwork()
            }
        }

        return@withContext Result.success(galleryList)
    }

    private suspend fun saveDataGalleryFromNetwork(): Result<GalleryCallBack> {
        val galleryList = galleryRemoteModel.getGalleryRemoteData()
        galleryList.getOrNull()?.let {
            galleryLocalModel.insertGallery(it.results)
        }
        return galleryList
    }
}