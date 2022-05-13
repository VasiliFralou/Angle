package by.vfdev.angle.UI.Gallery

import android.util.Log
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

    var data: Boolean = false
    var linkImages: String? = null

    val galleryLive: MutableLiveData<MutableList<Gallery>> by lazy {
        MutableLiveData<MutableList<Gallery>>()
    }

    fun getListGallery() {
        viewModelScope.launch {
            val data = galleryRepository.getDataGallery()
            data
                .onSuccess {
                    galleryLive.postValue(it)
                }.onFailure {
                    galleryLive.postValue(mutableListOf())
                    Log.e("!!!ErrorListGallery", it.stackTraceToString())
                }
        }
    }
}