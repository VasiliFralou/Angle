package by.vfdev.angle.UI.News

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.UI.Gallery.GalleryViewModel
import by.vfdev.angle.UI.MainActivity
import by.vfdev.angle.UI.Pilots.PilotsViewModel
import by.vfdev.angle.ViewModel.MainViewModel
import by.vfdev.angle.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news) {

    lateinit var navController: NavController
    lateinit var mainVM: MainViewModel
    lateinit var newsVM: NewsViewModel
    lateinit var galleryVM: GalleryViewModel
    lateinit var pilotsVM: PilotsViewModel

    private val binding by viewBinding(FragmentNewsBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainVM = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        newsVM = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
        galleryVM = ViewModelProvider(requireActivity())[GalleryViewModel::class.java]
        pilotsVM = ViewModelProvider(requireActivity())[PilotsViewModel::class.java]

        if (newsVM.newsList.value?.isEmpty() == true) {
            newsVM.initialize()
            // galleryVM.initialize()
        }

        navController = view.findNavController()

        binding.PostNewsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
        binding.PostNewsRV.adapter = PostNewsAdapter(newsVM.newsList.value!!, this)

        newsVM.newsList.observe(viewLifecycleOwner) {
            binding.PostNewsRV.adapter?.notifyDataSetChanged()
        }
    }

    fun showNewsDetails(position: Int) {
        newsVM.news = newsVM.newsList.value?.get(position)?.urlPost
        newsVM.titleNews = newsVM.newsList.value?.get(position)?.title
        navController.navigate(R.id.newsDetailFragment)
    }
}