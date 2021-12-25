package by.vfdev.angle.UI.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_news_detail.*

class NewsDetailFragment : DialogFragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NewsDetailsWV.loadUrl("${viewModel.news}")

        NewsDetailsWV.webChromeClient = WebChromeClient()
        NewsDetailsWV.webViewClient = WebViewClient()

        btnCloseNewsDetail.setOnClickListener {
            dismiss()
        }
    }
}