package by.vfdev.angle.UI.Events

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.EventsViewModel
import by.vfdev.angle.databinding.ViewPhotoEventsFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class ViewPhotoEventsDetailFragment : Fragment(R.layout.view_photo_events_fragment) {

    private val navController: NavController by lazy { findNavController() }
    private val eventsVM: EventsViewModel by activityViewModels()
    private val binding by viewBinding(ViewPhotoEventsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCloseImages.setOnClickListener {
            navController.popBackStack()
        }

        Glide.with(this)
            .load(eventsVM.link)
            .into(object : CustomTarget<Drawable?>() {
                @SuppressLint("SetTextI18n")
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    binding.fullImg.setImageDrawable(resource)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) = Unit
            })
    }
}