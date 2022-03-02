package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.vfdev.angle.R
import com.bumptech.glide.Glide
import androidx.annotation.Nullable
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.request.target.CustomTarget
import kotlinx.android.synthetic.main.fragment_gallery_detail.*


class GalleryDetailFragment : Fragment() {

    lateinit var galleryVM: GalleryViewModel
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        galleryVM = ViewModelProvider(requireActivity())[GalleryViewModel::class.java]

        return inflater.inflate(R.layout.fragment_gallery_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        Glide.with(this)
            .load(galleryVM.linkImages)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Drawable?>() {
                @SuppressLint("SetTextI18n")
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    full_img.setImageDrawable(resource)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) = Unit

            })

        btnCloseImages.setOnClickListener {
            navController.popBackStack()
        }
    }
}