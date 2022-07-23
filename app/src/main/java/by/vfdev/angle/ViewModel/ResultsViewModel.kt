package by.vfdev.angle.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.Results.Results
import by.vfdev.angle.Repository.ResultsRepository
import by.vfdev.angle.Utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val resultsRepository: ResultsRepository): ViewModel() {

    init {
        getListResults()
    }

    val resultsLive: MutableLiveData<MutableList<Results>> by lazy {
        MutableLiveData<MutableList<Results>>()
    }

    fun getListResults() {
        viewModelScope.launch {
            val list = resultsRepository.getDataResults()
            list.onSuccess {
                resultsLive.value = it
            }.onFailure {
                resultsLive.value = mutableListOf()
                Log.e("!!!ErrorListResults", it.stackTraceToString())
            }
        }
    }
}