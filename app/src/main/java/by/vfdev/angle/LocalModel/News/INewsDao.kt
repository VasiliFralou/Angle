package by.vfdev.angle.LocalModel.News

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.angle.RemoteModel.News.News

@Dao
interface INewsDao {

    @Insert
    suspend fun insertNews(news: MutableList<News>)

    @Query("SELECT * FROM News")
    suspend fun getAllNews(): MutableList<News>

    @Query("DELETE FROM News")
    suspend fun deleteAllEvents()
}