package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import by.vfdev.angle.R
import com.bumptech.glide.Glide
import androidx.annotation.Nullable
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.ViewModel.GalleryViewModel
import by.vfdev.angle.databinding.FragmentGalleryDetailBinding
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.request.target.CustomTarget


class GalleryDetailFragment : Fragment(R.layout.fragment_gallery_detail) {

    private val navController: NavController by lazy { findNavController() }

    private val galleryVM: GalleryViewModel by activityViewModels()
    private val binding by viewBinding(FragmentGalleryDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(galleryVM.linkImages)
            .into(object : CustomTarget<Drawable?>() {
                @SuppressLint("SetTextI18n")
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    binding.fullImg.setImageDrawable(resource)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) = Unit

            })

        binding.btnCloseImages.setOnClickListener {
            navController.popBackStack()
        }
    }
}