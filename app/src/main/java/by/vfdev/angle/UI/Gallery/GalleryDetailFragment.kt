package by.vfdev.angle.UI.Gallery

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
import kotlinx.android.synthetic.main.fragment_gallery_detail.*

class GalleryDetailFragment : DialogFragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return inflater.inflate(R.layout.fragment_gallery_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ImageWV.loadUrl("${viewModel.linkImages}")

        ImageWV.webChromeClient = WebChromeClient()
        ImageWV.webViewClient = WebViewClient()
        ImageWV.setInitialScale(30)
        ImageWV.settings.builtInZoomControls = true

        btnCloseImages.setOnClickListener {
            dismiss()
        }
    }
}