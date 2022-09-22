package by.vfdev.angle.LocalModel.News

import android.content.Context
import by.vfdev.angle.RemoteModel.News.News
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NewsLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = NewsDatabase.getDataBase(context).newsDao()

    suspend fun getAllNews(): List<News> {
        return database.getAllNews()
    }

    suspend fun insertNews(news: List<News>) {

        database.deleteAllEvents()
        database.insertNews(news)
    }
}