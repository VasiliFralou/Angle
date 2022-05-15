package by.vfdev.angle.RemoteModel.News

import android.util.Log
import by.vfdev.angle.Api.ApiNews
import java.lang.Exception
import javax.inject.Inject

class NewsRemoteModel @Inject constructor() {

    val apiNews = ApiNews.create()

    suspend fun getNewsRemoteData(): MutableList<News> {
        return try {
            val news = apiNews.getNews()
            news
        } catch (e: Exception) {
            mutableListOf()
        }
    }
}