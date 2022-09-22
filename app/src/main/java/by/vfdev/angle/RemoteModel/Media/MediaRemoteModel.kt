package by.vfdev.angle.RemoteModel.Media

import by.vfdev.angle.Api.ApiMedia
import javax.inject.Inject

class MediaRemoteModel @Inject constructor() {

    private val apiMedia = ApiMedia.create()

    suspend fun getMediaRemoteData(): MutableList<Media> {
        return try {
            val media: MutableList<Media> = apiMedia.getMedia().results
            media

        } catch (e: Exception) {
            mutableListOf()
        }
    }
}