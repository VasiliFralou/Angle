package by.vfdev.angle.UI.News

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private val navController: NavController by lazy { findNavController() }

    private val newsViewModel: NewsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentNewsDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleDetailNews.text = newsViewModel.titleNews

        binding.NewsDetailsWV.loadUrl("${newsViewModel.news}")

        binding.NewsDetailsWV.webChromeClient = WebChromeClient()
        binding.NewsDetailsWV.webViewClient = WebViewClient()

        binding.btnCloseNewsDetail.setOnClickListener {
            navController.popBackStack()
        }
    }
}