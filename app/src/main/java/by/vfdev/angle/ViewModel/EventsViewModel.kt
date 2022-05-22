package by.vfdev.angle.ViewModel

import android.util.Log
import android.widget.Toast
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

    init {
        getListEvents()
    }

    var latitudeEL: Double? = null
    var longitudeEL: Double? = null
    var titleEL: String? = null
    var nameEL: String? = null

    val eventsLive: MutableLiveData<MutableList<Events>> by lazy {
        MutableLiveData<MutableList<Events>>()
    }

    fun getListEvents() {
        viewModelScope.launch {
            val list = eventsRepository.getDataEvents()
            eventsLive.postValue(list)
        }
    }
}