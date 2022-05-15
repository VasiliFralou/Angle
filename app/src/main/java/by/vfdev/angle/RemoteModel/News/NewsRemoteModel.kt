package by.vfdev.angle.RemoteModel.News

import by.vfdev.angle.Api.ApiNews
import java.lang.Exception
import javax.inject.Inject

class NewsRemoteModel @Inject constructor() {

    private val apiNews = ApiNews.create()

    suspend fun getNewsRemoteData(): MutableList<News> {
        return try {
            val news: MutableList<News> = apiNews.getNews().results
            news
        } catch (e: Exception) {
            mutableListOf()
        }
    }
}