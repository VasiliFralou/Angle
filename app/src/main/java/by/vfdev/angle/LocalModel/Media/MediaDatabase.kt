package by.vfdev.angle.LocalModel.Media

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.vfdev.angle.RemoteModel.Media.Media

@Database(entities = [Media::class], version = 2)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun mediaDao(): IMediaDao

    companion object {
        private var mediaDatabase : MediaDatabase? = null
        fun getDataBase(contextApplication: Context): MediaDatabase {
            if (mediaDatabase == null) {
                mediaDatabase = Room.databaseBuilder(
                    contextApplication,
                    MediaDatabase::class.java, "media_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return mediaDatabase!!
        }
    }
}