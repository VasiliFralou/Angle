package by.vfdev.angle.RemoteModel.News

import by.vfdev.angle.Api.ApiNews
import javax.inject.Inject

class NewsRemoteModel @Inject constructor() {

    private val apiNews = ApiNews.create()

    suspend fun getNewsRemoteData(): List<News> {
        return try {
            val news: List<News> = apiNews.getNews().results
            news
        } catch (e: Exception) {
            mutableListOf()
        }
    }
}