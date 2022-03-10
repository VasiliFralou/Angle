package by.vfdev.angle.UI.Events

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_calendar.*

class EventsFragment : Fragment() {

    lateinit var navController: NavController
    private val eventsVM : EventsViewModel by activityViewModels()
    private val events = mutableListOf<Events>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        eventsVM.eventsLive.observe(activity as MainActivity) {
            events.clear()
            events.addAll(it)
            EventsRV.adapter?.notifyDataSetChanged()
        }

        val adapter = EventsLocationAdapter(events, this)
        EventsRV.adapter = adapter
        EventsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
    }

    fun showEventDetails(position: Int) {
        eventsVM.latitudeEL = events[position].latitude
        eventsVM.longitudeEL = events[position].longitude
        eventsVM.titleEL = events[position].title
        eventsVM.nameEL = events[position].name
        navController.navigate(R.id.eventsMapFragment)
    }

}