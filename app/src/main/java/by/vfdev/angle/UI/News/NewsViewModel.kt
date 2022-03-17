package by.vfdev.angle.UI.News

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.vfdev.angle.RemoteModel.News.News
import by.vfdev.angle.Repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    var titleNews: String? = null
    var data: Boolean = false
    var news: String? = null

    val scope = CoroutineScope(Dispatchers.IO)

    val newsLive: MutableLiveData<MutableList<News>> by lazy {
        MutableLiveData<MutableList<News>>()
    }

    fun getListNews() {
        scope.launch {
            val data = newsRepository.getDataNews()
            data
                .onSuccess {
                    newsLive.postValue(it)
                }.onFailure {
                    newsLive.postValue(mutableListOf())
                    Log.e("!!!ErrorListNews", it.stackTraceToString())
                }
        }
    }
}