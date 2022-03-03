package by.vfdev.angle.UI.News

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.UI.Calendar.CalendarViewModel
import by.vfdev.angle.UI.Gallery.GalleryViewModel
import by.vfdev.angle.UI.MainActivity
import by.vfdev.angle.UI.Pilots.PilotsViewModel
import by.vfdev.angle.ViewModel.MainViewModel
import by.vfdev.angle.databinding.FragmentNewsBinding
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment(R.layout.fragment_news) {

    lateinit var mainVM: MainViewModel
    lateinit var newsVM: NewsViewModel
//    lateinit var calendarVM: CalendarViewModel
    lateinit var galleryVM: GalleryViewModel
    lateinit var pilotsVM: PilotsViewModel

    lateinit var navController: NavController

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        mainVM = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        newsVM = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
//        calendarVM = ViewModelProvider(requireActivity())[CalendarViewModel::class.java]
        galleryVM = ViewModelProvider(requireActivity())[GalleryViewModel::class.java]
        pilotsVM = ViewModelProvider(requireActivity())[PilotsViewModel::class.java]

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (newsVM.newsList.value?.isEmpty() == true) {
            newsVM.initialize()
//            calendarVM.initialize()
            galleryVM.initialize()
        }

        navController = view.findNavController()

        PostNewsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
        PostNewsRV.adapter = PostNewsAdapter(newsVM.newsList.value!!, this)

        newsVM.newsList.observe(viewLifecycleOwner) {
            PostNewsRV.adapter?.notifyDataSetChanged()
        }
    }

    fun showNewsDetails(position: Int) {
        newsVM.news = newsVM.newsList.value?.get(position)?.urlPost
        newsVM.titleNews = newsVM.newsList.value?.get(position)?.title
        navController.navigate(R.id.newsDetailFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}