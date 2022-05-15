package by.vfdev.angle.RemoteModel.Gallery

import by.vfdev.angle.Api.ApiGallery
import javax.inject.Inject

class GalleryRemoteModel @Inject constructor() {

    private val apiGallery = ApiGallery.create()

    suspend fun getGalleryRemoteData(): MutableList<Gallery> {
        return try {
            val gallery: MutableList<Gallery> = apiGallery.getGallery().results
            gallery

        } catch (e: Exception) {
            mutableListOf()
        }
    }
}