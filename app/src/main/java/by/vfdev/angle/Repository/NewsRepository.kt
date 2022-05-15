package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.News.NewsLocalModel
import by.vfdev.angle.RemoteModel.News.News
import by.vfdev.angle.RemoteModel.News.NewsRemoteModel
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsRemoteModel: NewsRemoteModel,
                                         val newsLocalModel: NewsLocalModel) {

    suspend fun getDataNews(): MutableList<News> {

        var newsList = newsLocalModel.getAllNews()

        return if (newsList.isEmpty()) {
            newsList = newsRemoteModel.getNewsRemoteData()
            newsLocalModel.insertNews(newsList)
            newsList
        } else {
            newsList
        }
    }
}