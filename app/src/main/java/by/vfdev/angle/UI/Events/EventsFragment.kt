package by.vfdev.angle.UI.Events

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.EventsViewModel
import by.vfdev.angle.databinding.ListEventsFragmentBinding

class EventsFragment : Fragment(R.layout.list_events_fragment) {

    private val navController: NavController by lazy { findNavController() }
    private val eventsVM : EventsViewModel by activityViewModels()
    private val binding by viewBinding(ListEventsFragmentBinding::bind)


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EventsListAdapter(
            onClick = {
                eventsVM.onSelectEvents(it)
            }
        )

        binding.EventsRV.adapter = adapter
        binding.EventsRV.layoutManager = LinearLayoutManager(requireActivity())

        eventsVM.eventsLive.observe(viewLifecycleOwner) { list ->
            (binding.EventsRV.adapter as EventsListAdapter).updateData(list)
        }

        eventsVM.onSelectEventsEvent.observe(viewLifecycleOwner) {
            navController.navigate(R.id.eventsMapFragment)
        }

        binding.swipeEvents.setOnRefreshListener {
            getList(
                onSuccess = {
                    binding.swipeEvents.isRefreshing = false
                }
            )
        }
        binding.swipeEvents.setColorSchemeResources(R.color.firstColor)
    }

    private fun getList(onSuccess: () -> Unit) {
        eventsVM.getListEvents()
        onSuccess()
    }
}