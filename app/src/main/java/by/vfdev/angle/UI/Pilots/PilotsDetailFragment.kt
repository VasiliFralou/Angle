package by.vfdev.angle.UI.Pilots

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.PilotsViewModel
import by.vfdev.angle.databinding.DetailPilotsFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


class PilotsDetailFragment : Fragment(R.layout.detail_pilots_fragment) {

    private val navController: NavController by lazy { findNavController() }
    private val pilotsVM: PilotsViewModel by activityViewModels()
    private val binding by viewBinding(DetailPilotsFragmentBinding::bind)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClosePilotsDetails.setOnClickListener {
            navController.popBackStack()
        }

        pilotsVM.selectPilotsLD.observe(viewLifecycleOwner) { pilot ->

            val option = RequestOptions().placeholder(R.drawable.load)
                .error(R.drawable.load)
            Glide.with(this)
                .setDefaultRequestOptions(option)
                .load(pilot.photo)
                .into(binding.pilotsProfileIMG)

            Glide.with(this)
                .setDefaultRequestOptions(option)
                .load(pilot.photoAuto)
                .into(object : CustomTarget<Drawable?>() {
                    @SuppressLint("SetTextI18n")
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        binding.pilotsAutoImg.setImageDrawable(resource)
                    }

                    override fun onLoadCleared(@Nullable placeholder: Drawable?) = Unit

                })

            val brd = pilot.birthday
            val team = pilot.team
            val desc = pilot.description

            binding.pilotsNameTV.text = pilot.name
            binding.pilotsCityTV.text = "Город: ${pilot.city}"
            binding.pilotsBirthdayTV.text = brd ?: ("-").toString()
            binding.pilotsTeamTV.text = "Команда: ${team ?: ("-").toString()}"
            binding.pilotsInstTV.text = "Instagram: ${pilot.instagram}"
            binding.pilotsDescriptionTV.text = desc ?: ("-").toString()
            binding.pilotsAutoTV.text = "Авто: ${pilot.auto}"
        }
    }
}