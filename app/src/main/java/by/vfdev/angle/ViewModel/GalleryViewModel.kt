package by.vfdev.angle.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import by.vfdev.angle.Repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository) : ViewModel() {

    init {
        getListGallery()
    }

    var data: Boolean = false
    var linkImages: String? = null

    val galleryLive: MutableLiveData<MutableList<Gallery>> by lazy {
        MutableLiveData<MutableList<Gallery>>()
    }

    private fun getListGallery() {
        viewModelScope.launch {
            val list = galleryRepository.getDataGallery()
            galleryLive.postValue(list)
        }
    }
}