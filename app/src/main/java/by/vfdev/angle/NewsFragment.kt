package by.vfdev.angle

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initialize()

        PostNewsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
        PostNewsRV.adapter = PostNewsAdapter(viewModel.newsList.value!!, this)

        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            PostNewsRV.adapter?.notifyDataSetChanged()
        })
    }

    fun showNewsDetails(position: Int) {
        viewModel.news = viewModel.newsList.value?.get(position)?.urlPost
        NewsDetailFragment()
    }
}