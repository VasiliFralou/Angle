package by.vfdev.angle.UI.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_news_detail.*

class NewsDetailFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var newsViewModel: NewsViewModel
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        newsViewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        titleDetailNews.text = newsViewModel.titleNews

        NewsDetailsWV.loadUrl("${newsViewModel.news}")

        NewsDetailsWV.webChromeClient = WebChromeClient()
        NewsDetailsWV.webViewClient = WebViewClient()

        btnCloseNewsDetail.setOnClickListener {
            navController.popBackStack()
        }
    }
}