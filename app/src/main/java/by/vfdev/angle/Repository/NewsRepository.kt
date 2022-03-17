package by.vfdev.angle.Repository

import by.vfdev.angle.LocalModel.News.NewsLocalModel
import by.vfdev.angle.RemoteModel.News.News
import by.vfdev.angle.RemoteModel.News.NewsCallBack
import by.vfdev.angle.RemoteModel.News.NewsRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsRemoteModel: NewsRemoteModel,
    private val newsLocalModel: NewsLocalModel) {

    suspend fun getDataNews(): Result<MutableList<News>> = withContext(Dispatchers.IO) {

        val newsList = newsLocalModel.getAllNews()

        if (newsList.isEmpty()) {
            saveDataNewsFromNetwork()
        } else {
            launch {
                saveDataNewsFromNetwork()
            }
        }
        return@withContext Result.success(newsList)
    }

    private suspend fun saveDataNewsFromNetwork(): Result<NewsCallBack> {
        val newsList = newsRemoteModel.getNewsRemoteData()
        newsList.getOrNull()?.let {
            newsLocalModel.insertNews(it.results)
        }
        return newsList
    }
}