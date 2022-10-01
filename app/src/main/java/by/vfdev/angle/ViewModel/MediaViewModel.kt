package by.vfdev.angle.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.Media.Media
import by.vfdev.angle.Repository.MediaRepository
import by.vfdev.angle.Utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    private val mediaRepository: MediaRepository) : ViewModel() {

    init {
        getListMedia()
    }

    private val _selectMediaLD = MutableLiveData<Media>()
    val selectMediaLD: LiveData<Media> = _selectMediaLD

    private val _onSelectMediaEvent = SingleLiveEvent<Unit?>()
    val onSelectMediaEvent: LiveData<Unit?> = _onSelectMediaEvent

    fun onSelectMedia(media: Media) {
        _selectMediaLD.value = media
        _onSelectMediaEvent.call()
    }

    private var mediaLive: MutableList<Media> = mutableListOf()

    private var _searchListMedia = MutableLiveData<List<Media>>()
    val searchListMedia: LiveData<List<Media>> = _searchListMedia

    fun getListMedia() {
        viewModelScope.launch {
            val list = mediaRepository.getDataMedia()
            list.onSuccess {
                mediaLive = it
                _searchListMedia.value = mediaLive
            }.onFailure {
                _searchListMedia.value = mutableListOf()
                Log.e("!!!ErrorListMedia", it.stackTraceToString())
            }
        }
    }

    fun filteredMedia(text: String?) {

        val searchText = text?.lowercase(Locale.getDefault())
        val newList: List<Media> = mediaLive.filter {
            val lowName = it.tag.lowercase(Locale.getDefault())
            lowName.contains(searchText ?: "")
        }
        _searchListMedia.value = newList
    }
}