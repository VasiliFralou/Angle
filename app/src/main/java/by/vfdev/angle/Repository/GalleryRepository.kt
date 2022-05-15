package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Gallery.GalleryLocalModel
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import by.vfdev.angle.RemoteModel.Gallery.GalleryRemoteModel
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val galleryRemoteModel: GalleryRemoteModel,
    private val galleryLocalModel: GalleryLocalModel
) {

    suspend fun getDataGallery(): MutableList<Gallery> {

        var galleryList = galleryLocalModel.getAllGallery()

        return if (galleryList.isEmpty()) {
            galleryList = galleryRemoteModel.getGalleryRemoteData()
            galleryLocalModel.insertGallery(galleryList)
            galleryList
        } else {
            galleryList
        }
    }
}