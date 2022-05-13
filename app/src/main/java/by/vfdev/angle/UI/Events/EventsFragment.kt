package by.vfdev.angle.UI.Events

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.databinding.FragmentCalendarBinding

class EventsFragment : Fragment(R.layout.fragment_calendar) {

    private val navController: NavController by lazy { findNavController() }

    private val eventsVM : EventsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentCalendarBinding::bind)


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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