package by.vfdev.angle.UI.Calendar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.EventsLocation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalendarViewModel : ViewModel() {

    var data: Boolean = false
    var latitudeEL: Double? = null
    var longitudeEL: Double? = null
    var titleEL: String? = null
    var nameEL: String? = null

    var eventsLocationList = MutableLiveData<MutableList<EventsLocation>>(mutableListOf())

    val ref = "https://angle-571b8-default-rtdb.europe-west1.firebasedatabase.app/"
    val database = Firebase.database(ref)
    var dbrefEvents = database.getReference("Events")

    fun initialize() {
        viewModelScope.launch {
            GetEventsLocationList()
        }
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
}