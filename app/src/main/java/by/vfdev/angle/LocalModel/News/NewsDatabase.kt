package by.vfdev.angle.LocalModel.News

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.vfdev.angle.RemoteModel.News.News

@Database(entities = [News::class], version = 2)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): INewsDao

    companion object {
        private var newsDatabase : NewsDatabase? = null
        fun getDataBase(contextApplication: Context): NewsDatabase {
            if (newsDatabase == null) {
                newsDatabase = Room.databaseBuilder(
                    contextApplication,
                    NewsDatabase::class.java, "news_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return newsDatabase!!
        }
    }
}