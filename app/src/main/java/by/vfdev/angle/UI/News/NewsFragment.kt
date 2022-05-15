package by.vfdev.angle.UI.News

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.News.News
import by.vfdev.angle.UI.MainActivity
import by.vfdev.angle.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news), NewsListAdapter.OnItemClickListener {

    private val navController: NavController by lazy { findNavController() }

    private val newsVM: NewsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentNewsBinding::bind)
    private val posts = mutableListOf<News>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsVM.newsLive.observe(activity as MainActivity) {
            posts.clear()
            posts.addAll(it)
            binding.PostNewsRV.adapter?.notifyDataSetChanged()
        }

        val adapter = NewsListAdapter(posts, this)
        binding.PostNewsRV.adapter = adapter
        binding.PostNewsRV.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onItemClick(position: Int) {
        newsVM.news = newsVM.newsLive.value?.get(position)?.urlPost
        newsVM.titleNews = newsVM.newsLive.value?.get(position)?.title
        navController.navigate(R.id.newsDetailFragment)
    }
}