package by.vfdev.angle.UI.Events

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.isVisible
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
            eventsVM.title = binding.schemeLocationTitle.text.toString()
            eventsVM.link = eventsVM.selectEventsLD.value?.eventScheme.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }
        binding.judgesTask.setOnClickListener {
            eventsVM.title = binding.judgesTaskTitle.text.toString()
            eventsVM.link = eventsVM.selectEventsLD.value?.judgesTask.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }
        binding.resultsEventsIMG.setOnClickListener {
            eventsVM.title = binding.resultsEventsTitle.text.toString()
            eventsVM.link = eventsVM.selectEventsLD.value?.results.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }
        binding.qualificationIMG.setOnClickListener {
            eventsVM.title = binding.qualificationTitle.text.toString()
            eventsVM.link = eventsVM.selectEventsLD.value?.qualification.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }
        binding.gridResults.setOnClickListener {
            eventsVM.title = binding.gridResultsTitle.text.toString()
            eventsVM.link = eventsVM.selectEventsLD.value?.grid.toString()
            navController.navigate(R.id.eventsDetailFragment)
        }

        val listPilots = eventsVM.selectEventsLD.value?.listPilots
        if (listPilots.isNullOrEmpty()) {
            binding.pilotListEventsRV.isVisible = false
            binding.pilotListEventsTitle.isVisible = false
        } else {
            binding.pilotListEventsRV.layoutManager = LinearLayoutManager(requireActivity())
            binding.pilotListEventsRV.adapter = ListPilotsAdapter(listPilots)
        }

        eventsVM.selectEventsLD.observe(viewLifecycleOwner) { events ->

            binding.titleEvents.text = events.title
            binding.titleEventMap.text = events.name
            binding.posterIV.loadImage(events.promo)
            binding.dateEvents.text = events.date
            binding.location.text = events.location

            if (events.ticket.isNullOrEmpty()) {
                binding.ticketTitle.isVisible = false
                binding.btnTicket.isVisible = false
                binding.diTickets.isVisible = false
            }

            if (events.eventScheme.isNullOrEmpty()) {
                binding.schemeLocation.isVisible = false
                binding.schemeLocationTitle.isVisible = false
                binding.divSchemeLocation.isVisible = false
            } else binding.schemeLocation.loadImage(events.eventScheme)

            if (events.judgesTask.isNullOrEmpty()) {
                binding.judgesTask.isVisible = false
                binding.judgesTaskTitle.isVisible = false
                binding.divJudgesTask.isVisible = false
            } else binding.judgesTask.loadImage(events.judgesTask)

            if (events.results.isNullOrEmpty()) {
                binding.resultsEventsIMG.isVisible = false
                binding.resultsEventsTitle.isVisible = false
                binding.divResults.isVisible = false
            } else binding.resultsEventsIMG.loadImage(events.results)

            if (events.qualification.isNullOrEmpty()) {
                binding.qualificationIMG.isVisible = false
                binding.qualificationTitle.isVisible = false
                binding.divQualification.isVisible = false
            } else binding.qualificationIMG.loadImage(events.qualification)

            if (events.grid.isNullOrEmpty()) {
                binding.gridResults.isVisible = false
                binding.gridResultsTitle.isVisible = false
                binding.divGrid.isVisible = false
            } else binding.gridResults.loadImage(events.grid)
        }
    }
}