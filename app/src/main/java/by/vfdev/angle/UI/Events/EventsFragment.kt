package by.vfdev.angle.UI.Events

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.databinding.FragmentCalendarBinding

class EventsFragment : Fragment(R.layout.fragment_calendar) {

    lateinit var navController: NavController
    private val eventsVM : EventsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentCalendarBinding::bind)


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        val adapter = EventsListAdapter(this)
        binding.EventsRV.adapter = adapter
        binding.EventsRV.layoutManager = GridLayoutManager(
            requireActivity(), 1)

        eventsVM.eventsLive.observe(viewLifecycleOwner) { list ->
            (binding.EventsRV.adapter as EventsListAdapter).updateData(list)
        }
    }

    fun showEventDetails(position: Int) {
        eventsVM.latitudeEL = eventsVM.eventsLive.value?.get(position)?.latitude
        eventsVM.longitudeEL = eventsVM.eventsLive.value?.get(position)?.longitude
        eventsVM.titleEL = eventsVM.eventsLive.value?.get(position)?.title
        eventsVM.nameEL = eventsVM.eventsLive.value?.get(position)?.name
        navController.navigate(R.id.eventsMapFragment)
    }

}