package by.vfdev.angle.UI.News

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.NewsViewModel
import by.vfdev.angle.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news),
    NewsListAdapter.OnItemClickListener {

    private val navController: NavController by lazy { findNavController() }
    private val newsVM: NewsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentNewsBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsListAdapter(this)

        binding.PostNewsRV.adapter = adapter
        binding.PostNewsRV.layoutManager = LinearLayoutManager(requireActivity())

        newsVM.newsLive.observe(viewLifecycleOwner) { list ->
            (binding.PostNewsRV.adapter as NewsListAdapter).updateData(list)
        }
    }

    override fun onItemClick(position: Int) {

        val item = newsVM.newsLive.value?.get(position)

        newsVM.news = item?.urlPost
        newsVM.titleNews = item?.title

        navController.navigate(R.id.newsDetailFragment)
    }
}