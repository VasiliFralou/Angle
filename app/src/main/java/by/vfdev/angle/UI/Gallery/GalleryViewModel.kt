package by.vfdev.angle.UI.Gallery

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.Gallery
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GalleryViewModel : ViewModel() {

    var galleryList = MutableLiveData<MutableList<Gallery>>(mutableListOf())
    var data: Boolean = false

    val ref = "https://angle-571b8-default-rtdb.europe-west1.firebasedatabase.app/"
    val database = Firebase.database(ref)
    var dbrefGallery = database.getReference("Images")

    fun initialize() {
        viewModelScope.launch {
            GetGalleryPhotoList()
        }
    }

    suspend fun GetGalleryPhotoList() = withContext(Dispatchers.IO) {
        dbrefGallery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data = true
                if (snapshot.exists()) {
                    for (scoresSnapshot in snapshot.children) {
                        val gallery = scoresSnapshot.getValue(Gallery::class.java)
                        galleryList.value?.add(gallery!!)
                        galleryList.value = galleryList.value

                        Log.e("!!!FB", galleryList.value!!.size.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("!!!Error", "messages:onCancelled: ${error.message}")
            }
        })
    }
}