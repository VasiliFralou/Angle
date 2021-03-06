package by.vfdev.angle.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.News.News
import by.vfdev.angle.Repository.NewsRepository
import by.vfdev.angle.Utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository) : ViewModel() {

    init {
        getListNews()
    }

    var link: String? = null

    private val _selectNewsLD = MutableLiveData<News>()
    val selectNewsLD: LiveData<News> = _selectNewsLD

    fun onSelectNews(news: News) {
        _selectNewsLD.value = news
    }

    val newsLive: MutableLiveData<MutableList<News>> by lazy {
        MutableLiveData<MutableList<News>>()
    }

    fun getListNews() {
        viewModelScope.launch {
            val list = newsRepository.getDataNews()
            list.onSuccess {
                newsLive.value = it
            }.onFailure {
                newsLive.value = mutableListOf()
                Log.e("!!!ErrorListNews", it.stackTraceToString())
            }
        }
    }
}