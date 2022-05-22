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
import by.vfdev.angle.ViewModel.NewsViewModel
import by.vfdev.angle.databinding.DetailNewsFragmentBinding

class NewsDetailFragment : Fragment(R.layout.detail_news_fragment) {

    private val navController: NavController by lazy { findNavController() }
    private val newsVM: NewsViewModel by activityViewModels()
    private val binding by viewBinding(DetailNewsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCloseNewsDetail.setOnClickListener {
            navController.popBackStack()
        }

        newsVM.selectNewsLD.observe(viewLifecycleOwner) { news ->

            binding.titleDetailNews.text = news.title
            binding.NewsDetailsWV.loadUrl(news.urlPost)

            binding.NewsDetailsWV.webChromeClient = WebChromeClient()
            binding.NewsDetailsWV.webViewClient = WebViewClient()
        }
    }
}