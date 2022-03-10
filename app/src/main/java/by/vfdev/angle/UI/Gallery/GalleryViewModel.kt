package by.vfdev.angle.UI.Gallery

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import by.vfdev.angle.Repository.GalleryRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    var data: Boolean = false
    var galleryList = MutableLiveData<MutableList<Gallery>>(mutableListOf())
    var linkImages: String? = null

    val scope = CoroutineScope(Dispatchers.IO)

    val galleryLive: MutableLiveData<MutableList<Gallery>> by lazy {
        MutableLiveData<MutableList<Gallery>>()
    }

    fun getListGallery() {
        scope.launch {
            val data = galleryRepository.getDataGallery()
            data
                .onSuccess {
                    galleryLive.postValue(it.results)
                    Log.e("!!!GGGGGGGGG", it.toString())
                }.onFailure {
                    galleryLive.postValue(mutableListOf())
                    Log.e("!!!ErrorListGallery", it.stackTraceToString())
                }
        }
    }
}