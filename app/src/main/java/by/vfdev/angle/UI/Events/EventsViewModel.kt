package by.vfdev.angle.UI.Events

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.Repository.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    var latitudeEL: Double? = null
    var longitudeEL: Double? = null
    var titleEL: String? = null
    var nameEL: String? = null

    val eventsLive: MutableLiveData<MutableList<Events>> by lazy {
        MutableLiveData<MutableList<Events>>()
    }

    fun getListEvents() {
        viewModelScope.launch {
            val data = eventsRepository.getDataEvents()
            data
                .onSuccess {
                    eventsLive.postValue(it)
                }.onFailure {
                    eventsLive.postValue(mutableListOf())
                    Log.e("!!!ErrorListEvents", it.stackTraceToString())
                }
        }
    }
}