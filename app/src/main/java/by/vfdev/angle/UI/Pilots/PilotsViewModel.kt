package by.vfdev.angle.UI.Pilots

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.Pilots.Pilots
import by.vfdev.angle.Repository.PilotsRepository
import by.vfdev.angle.Utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PilotsViewModel @Inject constructor(
    private val pilotsRepository: PilotsRepository
) : ViewModel() {


    private val _selectPilotsLD = MutableLiveData<Pilots>()
    val selectPilotsLD: LiveData<Pilots> = _selectPilotsLD

    private val _onSelectPilotsEvent = SingleLiveEvent<Unit?>()
    val onSelectPilotsEvent: LiveData<Unit?> = _onSelectPilotsEvent
    fun onSelectPilots(pilot: Pilots) {
        _selectPilotsLD.value = pilot
        _onSelectPilotsEvent.call()
    }


    private var pilotsList: MutableList<Pilots> = mutableListOf()


    private val _pilotsSearchList = MutableLiveData<List<Pilots>>()
    val pilotsSearchList: LiveData<List<Pilots>> = _pilotsSearchList


    fun getListPilots() {
        viewModelScope.launch {
            val data = pilotsRepository.getDataPilots()
            data
                .onSuccess {
                    pilotsList = it
                    _pilotsSearchList.value = pilotsList
                }.onFailure {
                    _pilotsSearchList.postValue(mutableListOf())
                    Log.e("!!!ErrorListGallery", it.stackTraceToString())
                }
        }
    }


    fun filteredPilots(text: String?) {
        val searchText = text?.lowercase(Locale.getDefault())
        val newList: List<Pilots> = pilotsList.filter {
            val lowName = it.name.lowercase(Locale.getDefault())
            lowName.contains(searchText ?: "")
        }
        _pilotsSearchList.value = newList
    }
}