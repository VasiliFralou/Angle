package by.vfdev.angle.ViewModel

import android.util.Log
import androidx.lifecycle.*
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

    init {
        getListPilots()
    }

    private val _selectPilotsLD = MutableLiveData<Pilots>()
    val selectPilotsLD: LiveData<Pilots> = _selectPilotsLD

    private val _onSelectPilotsEvent = SingleLiveEvent<Unit?>()
    val onSelectPilotsEvent: LiveData<Unit?> = _onSelectPilotsEvent

    var link: String = ""

    fun onSelectPilots(pilot: Pilots) {
        _selectPilotsLD.value = pilot
        _onSelectPilotsEvent.call()
    }

    private var pilotsLive: MutableList<Pilots> = mutableListOf()

    private var _searchListPilots = MutableLiveData<List<Pilots>>()
    val searchListPilots: LiveData<List<Pilots>> = _searchListPilots

    private fun getListPilots() {
        viewModelScope.launch {
            val list = pilotsRepository.getDataPilots()
            list.onSuccess {
                pilotsLive = it
                _searchListPilots.value = pilotsLive
            }.onFailure {
                _searchListPilots.value = mutableListOf()
                Log.e("!!!ErrorListPilots", it.stackTraceToString())
            }
        }
    }

    fun filteredPilots(text: String?) {

        val searchText = text?.lowercase(Locale.getDefault())
        val newList: List<Pilots> = pilotsLive.filter {
            val lowName = it.name.lowercase(Locale.getDefault())
            lowName.contains(searchText ?: "")
        }
        _searchListPilots.value = newList
    }
}