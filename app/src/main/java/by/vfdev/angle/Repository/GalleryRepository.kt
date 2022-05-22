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

    suspend fun getDataGallery(): MutableList<Gallery> = withContext(Dispatchers.IO) {

        var galleryList = galleryRemoteModel.getGalleryRemoteData()

        if (galleryList.isNullOrEmpty()) {
            galleryList = galleryLocalModel.getAllGallery()
        }  else {
            launch {
                updateDataGalleryFromDB()
            }
        }
        return@withContext galleryList
    }

    private suspend fun updateDataGalleryFromDB(): MutableList<Gallery> {

        val galleryList = galleryRemoteModel.getGalleryRemoteData()
        galleryLocalModel.insertGallery(galleryList)

        return galleryList
    }
}