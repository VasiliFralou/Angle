package by.vfdev.angle.UI.News

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.angle.RemoteModel.News
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewsViewModel : ViewModel() {

    var titleNews: String? = null
    var data: Boolean = false
    var news: String? = null

    var newsList = MutableLiveData<MutableList<News>>(mutableListOf())

    val ref = "https://angle-571b8-default-rtdb.europe-west1.firebasedatabase.app/"
    val database = Firebase.database(ref)
    var dbrefNews = database.getReference("News")

    fun initialize() {
        viewModelScope.launch {
            GetNewsList()
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
}