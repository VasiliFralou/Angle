package by.vfdev.angle.RemoteModel.News

import by.vfdev.angle.Api.ApiNews
import java.lang.Exception
import javax.inject.Inject

class NewsRemoteModel @Inject constructor() : INewsRemoteModel {

    private val apiNews = ApiNews.create()

    override suspend fun getNewsRemoteData(): Result<NewsCallBack> {
        return try {
            val news: NewsCallBack = apiNews.getNews()
            Result.success(news)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}