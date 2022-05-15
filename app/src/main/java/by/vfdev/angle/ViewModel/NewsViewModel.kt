package by.vfdev.angle.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.News.News
import by.vfdev.angle.Repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    init {
        getListNews()
    }

    var titleNews: String? = null
    var data: Boolean = false
    var news: String? = null

    val newsLive: MutableLiveData<MutableList<News>> by lazy {
        MutableLiveData<MutableList<News>>()
    }

    private fun getListNews() {
        viewModelScope.launch {
            val list = newsRepository.getDataNews()
            newsLive.postValue(list)
        }
    }
}