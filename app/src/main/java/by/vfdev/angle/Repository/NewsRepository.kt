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

    suspend fun getDataNews():
            Result<MutableList<News>> = withContext(Dispatchers.IO) {

        val newsListEthernet = newsRemoteModel.getNewsRemoteData()
        var newsListLocal = newsLocalModel.getAllNews()

        if (newsListLocal.isEmpty()) {
            newsListLocal = updateDataNewsFromDB(newsListEthernet)
        } else {
            launch {
                updateDataNewsFromDB(newsListEthernet)
            }
        }
        return@withContext Result.success(newsListLocal)
    }

    private suspend fun updateDataNewsFromDB(list: MutableList<News>): MutableList<News> {

        if (list.isNotEmpty()) {
            newsLocalModel.insertNews(list)
        }
        return list
    }
}