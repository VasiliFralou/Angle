package by.vfdev.angle.LocalModel.Media

import android.content.Context
import by.vfdev.angle.RemoteModel.Media.Media
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MediaLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = MediaDatabase.getDataBase(context).mediaDao()

    suspend fun insertMedia(media: MutableList<Media>) {
        database.deleteAllMedia()
        database.insertMedia(media)
    }

    suspend fun getAllMedia() : MutableList<Media> {
        return database.getAllMedia()
    }
}