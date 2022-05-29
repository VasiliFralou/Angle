package by.vfdev.angle.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import by.vfdev.angle.Repository.GalleryRepository
import by.vfdev.angle.Utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository) : ViewModel() {

    init {
        getListGallery()
    }

    private val _selectGalleryLD = MutableLiveData<Gallery>()
    val selectGalleryLD: LiveData<Gallery> = _selectGalleryLD

    private val _onSelectGalleryEvent = SingleLiveEvent<Unit?>()
    val onSelectGalleryEvent: LiveData<Unit?> = _onSelectGalleryEvent

    fun onSelectGallery(gallery: Gallery) {
        _selectGalleryLD.value = gallery
        _onSelectGalleryEvent.call()
    }

    val galleryLive: MutableLiveData<MutableList<Gallery>> by lazy {
        MutableLiveData<MutableList<Gallery>>()
    }

    fun getListGallery() {
        viewModelScope.launch {
            val list = galleryRepository.getDataGallery()
            galleryLive.postValue(list)
        }
    }
}