package by.vfdev.angle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private lateinit var messagesListener: ValueEventListener
    private val newsList : MutableList<News> = ArrayList()

    val ref = "https://angle-571b8-default-rtdb.europe-west1.firebasedatabase.app/"
    val database = Firebase.database(ref)
    val dbref = database.getReference("News")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(PostNewsRV)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        messagesListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                newsList.clear()
                dataSnapshot.children.forEach { child ->
                    val news: News = News(
                        child.child("title").getValue<String>(),
                        child.child("description").getValue<String>(),
                        child.child("date").getValue<String>(),
                        child.child("urlImg").getValue<String>(),
                        child.child("urlPost").getValue<String>(),
                        child.key)
                    news.let { newsList.add(it) }
                }
                newsList.reverse()
                PostNewsRV.layoutManager = LinearLayoutManager(activity as MainActivity)
                PostNewsRV.adapter = PostNewsViewAdapter(newsList, this)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", "messages:onCancelled: ${error.message}")
            }
        }
        dbref.addValueEventListener(messagesListener)
    }
}