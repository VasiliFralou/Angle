package by.vfdev.angle.UI.Media

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import by.vfdev.angle.R
import com.bumptech.glide.Glide
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.ViewModel.MediaViewModel
import by.vfdev.angle.databinding.DetailMediaFragmentBinding
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.request.target.CustomTarget


class MediaDetailFragment : Fragment(R.layout.detail_media_fragment) {

    private val navController: NavController by lazy { findNavController() }
    private val mediaVM: MediaViewModel by activityViewModels()
    private val binding by viewBinding(DetailMediaFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCloseImages.setOnClickListener {
            navController.popBackStack()
        }

        mediaVM.selectMediaLD.observe(viewLifecycleOwner) { media ->
            binding.tileDetailMedia.text = mediaVM.selectMediaLD.value?.title
            Glide.with(this)
                .load(media.image)
                .into(object : CustomTarget<Drawable?>() {
                    @SuppressLint("SetTextI18n")
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                        binding.fullImg.setImageDrawable(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) = Unit

                })
        }
    }
}