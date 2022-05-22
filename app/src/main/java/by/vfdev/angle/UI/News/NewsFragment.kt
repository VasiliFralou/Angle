package by.vfdev.angle.UI.News

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.NewsViewModel
import by.vfdev.angle.databinding.ListNewsFragmentBinding
import java.util.*
import kotlin.concurrent.schedule

class NewsFragment : Fragment(R.layout.list_news_fragment) {

    private val navController: NavController by lazy { findNavController() }
    private val newsVM: NewsViewModel by activityViewModels()
    private val binding by viewBinding(ListNewsFragmentBinding::bind)

    @SuppressLint("NotifyDataSetChanged", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsListAdapter(
            onClick = {
                newsVM.onSelectNews(it)
            }
        )

        binding.PostNewsRV.adapter = adapter
        binding.PostNewsRV.layoutManager = LinearLayoutManager(requireActivity())

        newsVM.newsLive.observe(viewLifecycleOwner) { list ->
            (binding.PostNewsRV.adapter as NewsListAdapter).updateData(list)
        }

        newsVM.onSelectNewsEvent.observe(viewLifecycleOwner) {
            navController.navigate(R.id.newsDetailFragment)
        }

        binding.swipeNews.setOnRefreshListener {
            getList(
                onSuccess = {
                    binding.swipeNews.isRefreshing = false
                }
            )
        }

        binding.swipeNews.setColorSchemeResources(R.color.firstColor)
    }

    private fun getList(onSuccess: () -> Unit) {
        newsVM.getListNews()
        onSuccess()
    }
}