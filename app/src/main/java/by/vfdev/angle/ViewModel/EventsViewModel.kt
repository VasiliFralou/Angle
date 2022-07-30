package by.vfdev.angle.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.Repository.EventsRepository
import by.vfdev.angle.Utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository) : ViewModel() {

    init {
        getListEvents()
    }

    private val _selectEventsLD = MutableLiveData<Events>()
    val selectEventsLD: LiveData<Events> = _selectEventsLD

    private val _onSelectEventsEvent = SingleLiveEvent<Unit?>()
    val onSelectEventsEvent: LiveData<Unit?> = _onSelectEventsEvent

    fun onSelectEvents(event: Events) {
        _selectEventsLD.value = event
        _onSelectEventsEvent.call()
    }

    var link: String = ""

    val eventsLive: MutableLiveData<MutableList<Events>> by lazy {
        MutableLiveData<MutableList<Events>>()
    }

    fun getListEvents() {
        viewModelScope.launch {
            val list = eventsRepository.getDataEvents()
            list.onSuccess {
                eventsLive.value = it
            }.onFailure {
                eventsLive.value = mutableListOf()
                Log.e("!!!ErrorListNews", it.stackTraceToString())
            }
        }
    }
}