package by.vfdev.angle.LocalModel.Gallery

import android.content.Context
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GalleryLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = GalleryDatabase.getDataBase(context).galleryDao()

    suspend fun insertGallery(gallery: MutableList<Gallery>) {
        database.deleteAllImages()
        database.insertGallery(gallery)
    }

    suspend fun getAllGallery() : MutableList<Gallery> {
        return database.getAllGallery()
    }
}