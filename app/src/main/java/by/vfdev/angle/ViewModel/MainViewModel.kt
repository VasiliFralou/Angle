package by.vfdev.angle.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.News
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.EventsLocation
import by.vfdev.angle.RemoteModel.Gallery
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    var data: Boolean = false
    var newsList = MutableLiveData<MutableList<News>>(mutableListOf())
    var eventsLocationList = MutableLiveData<MutableList<EventsLocation>>(mutableListOf())
    var galleryList = MutableLiveData<MutableList<Gallery>>(mutableListOf())

    var news: String? = null
    var latitudeEL: Double? = null
    var longitudeEL: Double? = null
    var titleEL: String? = null
    var linkImages: String? = null

    val ref = "https://angle-571b8-default-rtdb.europe-west1.firebasedatabase.app/"
    val tabNumbers: Array<Int> = arrayOf(
        R.drawable.ic_news,
        R.drawable.ic_calendar,
        R.drawable.ic_gallery,
        R.drawable.ic_pilots,
        R.drawable.ic_settings)
    val database = Firebase.database(ref)
    var dbrefNews = database.getReference("News")
    var dbrefEvents = database.getReference("Events")
    var dbrefGallery = database.getReference("Images")

    fun initialize() {
        viewModelScope.launch {
            GetNewsList()
        }
        viewModelScope.launch {
            GetEventsLocationList()
        }
        viewModelScope.launch {
            GetGalleryPhotoList()
        }
    }

    suspend fun GetNewsList() = withContext(Dispatchers.IO) {
        dbrefNews.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data = true
                if (snapshot.exists()) {
                    for (scoresSnapshot in snapshot.children) {
                        val news = scoresSnapshot.getValue(News::class.java)
                        newsList.value?.add(news!!)
                    }
                    newsList.value?.reverse()
                    newsList.value = newsList.value
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("!!!Error", "messages:onCancelled: ${error.message}")
            }
        })
    }

    suspend fun GetEventsLocationList() = withContext(Dispatchers.IO) {
        dbrefEvents.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data = true
                if (snapshot.exists()) {
                    for (scoresSnapshot in snapshot.children) {
                        val eventsLocation = scoresSnapshot.getValue(EventsLocation::class.java)
                        eventsLocationList.value?.add(eventsLocation!!)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("!!!Error", "messages:onCancelled: ${error.message}")
            }
        })
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
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("!!!Error", "messages:onCancelled: ${error.message}")
            }
        })
    }
}