package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.Media.MediaLocalModel
import by.vfdev.angle.RemoteModel.Media.Media
import by.vfdev.angle.RemoteModel.Media.MediaRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val mediaRemoteModel: MediaRemoteModel,
    private val mediaLocalModel: MediaLocalModel) {

    suspend fun getDataMedia():
            Result<MutableList<Media>> = withContext(Dispatchers.IO) {

        val mediaListEthernet = mediaRemoteModel.getMediaRemoteData()
        var mediaListLocal = mediaLocalModel.getAllMedia()

        if (mediaListLocal.isEmpty()) {
            mediaListLocal = updateDataMediaFromDB(mediaListEthernet)
        } else {
            launch {
                updateDataMediaFromDB(mediaListEthernet)
            }
        }
        return@withContext Result.success(mediaListLocal)
    }

    private suspend fun updateDataMediaFromDB(list: MutableList<Media>): MutableList<Media> {

        if (list.isNotEmpty()) {
            mediaLocalModel.insertMedia(list)
        }
        return list
    }
}