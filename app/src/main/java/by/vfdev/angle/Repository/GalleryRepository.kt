package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Gallery.GalleryLocalModel
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import by.vfdev.angle.RemoteModel.Gallery.GalleryRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val galleryRemoteModel: GalleryRemoteModel,
    private val galleryLocalModel: GalleryLocalModel
) {

    suspend fun getDataGallery():
            Result<MutableList<Gallery>> = withContext(Dispatchers.IO) {

        val galleryListEthernet = galleryRemoteModel.getGalleryRemoteData()
        var galleryListLocal = galleryLocalModel.getAllGallery()

        if (galleryListLocal.isEmpty()) {
            galleryListLocal = updateDataGalleryFromDB(galleryListEthernet)
        } else {
            launch {
                updateDataGalleryFromDB(galleryListEthernet)
            }
        }
        return@withContext Result.success(galleryListLocal)
    }

    private suspend fun updateDataGalleryFromDB(list: MutableList<Gallery>): MutableList<Gallery> {

        if (list.isNotEmpty()) {
            galleryLocalModel.insertGallery(list)
        }

        return list
    }
}