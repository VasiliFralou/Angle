package by.vfdev.angle.UI.News

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

    private val scope = CoroutineScope(Dispatchers.IO)

    val newsLive: MutableLiveData<MutableList<News>> by lazy {
        MutableLiveData<MutableList<News>>()
    }

    fun getListNews() {
        scope.launch {
            val list = newsRepository.getDataNews()
            newsLive.postValue(list)
        }
    }
}