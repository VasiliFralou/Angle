package by.vfdev.angle.UI.News

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val navController: NavController by lazy { findNavController() }

    private val newsVM: NewsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentNewsBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = NewsListAdapter( this)
        binding.PostNewsRV.adapter = adapter
        binding.PostNewsRV.layoutManager = GridLayoutManager(
            requireActivity(), 1)

        newsVM.newsLive.observe(viewLifecycleOwner) { list ->
            (binding.PostNewsRV.adapter as NewsListAdapter).updateData(list)
        }
    }



    fun showNewsDetails(position: Int) {
        newsVM.news = newsVM.newsLive.value?.get(position)?.urlPost
        newsVM.titleNews = newsVM.newsLive.value?.get(position)?.title
        navController.navigate(R.id.newsDetailFragment)
    }
}