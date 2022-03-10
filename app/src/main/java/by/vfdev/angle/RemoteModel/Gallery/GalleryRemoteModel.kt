package by.vfdev.angle.RemoteModel.Gallery

import by.vfdev.angle.Api.ApiGallery
import by.vfdev.angle.RemoteModel.Events.EventsCallBack
import javax.inject.Inject

class GalleryRemoteModel @Inject constructor() : IGalleryRemoteModel {

    private val apiGallery = ApiGallery.create()

    override suspend fun getGalleryRemoteData(): Result<GalleryCallBack> {
        return try {
            val gallery: GalleryCallBack = apiGallery.getGallery()
            Result.success(gallery)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}