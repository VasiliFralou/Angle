package by.vfdev.angle.Repository

import android.util.Log
import by.vfdev.angle.LocalModel.News.NewsLocalModel
import by.vfdev.angle.RemoteModel.News.News
import by.vfdev.angle.RemoteModel.News.NewsRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsRemoteModel: NewsRemoteModel,
    private val newsLocalModel: NewsLocalModel) {

    suspend fun getDataNews(): Result<MutableList<News>> = withContext(Dispatchers.IO) {

        var newsList = newsRemoteModel.getNewsRemoteData()

        if (newsList.isNullOrEmpty()) {
            newsList = newsLocalModel.getAllNews()
        }  else {
            launch {
                updateDataNewsFromDB()
            }
        }
        return@withContext Result.success(newsList)
    }

    private suspend fun updateDataNewsFromDB(): MutableList<News> {

        val newsList = newsRemoteModel.getNewsRemoteData()
        newsLocalModel.insertNews(newsList)

        return newsList
    }
}