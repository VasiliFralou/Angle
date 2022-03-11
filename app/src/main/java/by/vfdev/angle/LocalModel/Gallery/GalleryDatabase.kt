package by.vfdev.angle.LocalModel.Gallery

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.vfdev.angle.RemoteModel.Gallery.Gallery

@Database(entities = [Gallery::class], version = 1)
abstract class GalleryDatabase : RoomDatabase() {
    abstract fun galleryDao(): IGalleryDao

    companion object {
        private var galleryDatabase : GalleryDatabase? = null
        fun getDataBase(contextApplication: Context): GalleryDatabase {
            if (galleryDatabase == null) {
                galleryDatabase = Room.databaseBuilder(
                    contextApplication,
                    GalleryDatabase::class.java, "gallery_db"
                ).build()
            }
            return galleryDatabase!!
        }
    }
}