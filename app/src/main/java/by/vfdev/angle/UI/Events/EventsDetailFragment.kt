package by.vfdev.angle.UI.Events

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.UI.Dialog.LinkDialogFragment
import by.vfdev.angle.UI.Dialog.MapDialogFragment
import by.vfdev.angle.Utils.loadImage
import by.vfdev.angle.ViewModel.EventsViewModel
import by.vfdev.angle.databinding.DetailEventsFragmentBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class EventsDetailFragment : Fragment(R.layout.detail_events_fragment) {

    private val navController: NavController by lazy { findNavController() }
    private val eventsVM: EventsViewModel by activityViewModels()
    private val binding by viewBinding(DetailEventsFragmentBinding::bind)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCloseEventsMap.setOnClickListener {
            navController.popBackStack()
        }

        binding.btnCheckMap.setOnClickListener {
            val dialog = MapDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "customDialog")
        }

        binding.btnTicket.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(eventsVM.selectEventsLD.value?.ticket))
            startActivity(browserIntent)
        }

        binding.schemeLocation.setOnClickListener {
            eventsVM.link = eventsVM.selectEventsLD.value?.eventScheme.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }

        binding.judgesTask.setOnClickListener {
            eventsVM.link = eventsVM.selectEventsLD.value?.judgesTask.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }

        binding.resultsEventsIMG.setOnClickListener {
            eventsVM.link = eventsVM.selectEventsLD.value?.results.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }

        binding.qualificationIMG.setOnClickListener {
            eventsVM.link = eventsVM.selectEventsLD.value?.qualification.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }

        binding.gridResults.setOnClickListener {
            eventsVM.link = eventsVM.selectEventsLD.value?.grid.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }

        val listPilots = eventsVM.selectEventsLD.value?.listPilots
        binding.pilotListEventsRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.pilotListEventsRV.adapter = ListPilotsAdapter(listPilots!!)

        eventsVM.selectEventsLD.observe(viewLifecycleOwner) { events ->

            binding.titleEventMap.text = events.name
            binding.posterIV.loadImage(events.promo)
            binding.dateEvents.text = events.date
            binding.location.text = events.location
            binding.schemeLocation.loadImage(events.eventScheme)

            binding.pilotListEventsRV.adapter as ListPilotsAdapter

            binding.judgesTask.loadImage(events.judgesTask)
            binding.resultsEventsIMG.loadImage(events.results)
            binding.qualificationIMG.loadImage(events.qualification)
            binding.gridResults.loadImage(events.grid)
        }
    }
}