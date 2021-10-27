package by.vfdev.angle.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.News
import by.vfdev.angle.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    var data: Boolean = false
    var newsList = MutableLiveData<MutableList<News>>(mutableListOf())
    var news: String? = null

    val ref = "https://angle-571b8-default-rtdb.europe-west1.firebasedatabase.app/"
    val tabNumbers: Array<Int> = arrayOf(
        R.drawable.ic_news,
        R.drawable.ic_pilots,
        R.drawable.ic_settings
    )
    val database = Firebase.database(ref)
    var dbref = database.getReference("News")

    fun initialize() {
        viewModelScope.launch {
            GetNewsList()
        }
    }

    suspend fun GetNewsList() = withContext(Dispatchers.IO) {
        dbref.addValueEventListener(object : ValueEventListener {
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
}