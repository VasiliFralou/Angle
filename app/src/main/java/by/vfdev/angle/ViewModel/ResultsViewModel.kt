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

    private val _selectResultsLD = MutableLiveData<Results>()
    val selectResultsLD: LiveData<Results> = _selectResultsLD

    private val _onSelectResultsEvent = SingleLiveEvent<Unit?>()
    val onSelectResultsEvent: LiveData<Unit?> = _onSelectResultsEvent

    fun onSelectResults(results: Results) {
        _selectResultsLD.value = results
        _onSelectResultsEvent.call()
    }

    val resultsLive: MutableLiveData<MutableList<Results>> by lazy {
        MutableLiveData<MutableList<Results>>()
    }

    fun getListResults() {
        viewModelScope.launch {
            val list = resultsRepository.getDataResults()
            list.onSuccess {
                resultsLive.postValue(it)
            }.onFailure {
                resultsLive.postValue(mutableListOf())
                Log.e("!!!ErrorListResults", it.stackTraceToString())
            }
        }
    }
}