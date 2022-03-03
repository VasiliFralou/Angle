package by.vfdev.angle.UI.Calendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.EventsLocation
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {

    lateinit var calendarVM: CalendarViewModel
    lateinit var navController: NavController

    private val event = mutableListOf<EventsLocation>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        calendarVM = ViewModelProvider(activity as MainActivity)[CalendarViewModel::class.java]

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        calendarVM.eventsLive.observe(activity as MainActivity, Observer {
            event.clear()
            event.addAll(it)
            EventsRV.adapter?.notifyDataSetChanged()
        })

        Log.d("!!!", event.toString())

        val adapter = EventsLocationAdapter(event, this)
        EventsRV.adapter = adapter
        EventsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)

//        EventsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
//        EventsRV.adapter = EventsLocationAdapter(calendarVM.eventsLocationList.value!!, this)
//
//        calendarVM.eventsLocationList.observe(viewLifecycleOwner) {
//            EventsRV.adapter?.notifyDataSetChanged()
//        }
    }

    fun showEventDetails(position: Int) {
        calendarVM.latitudeEL = calendarVM.eventsLocationList.value?.get(position)?.latitude
        calendarVM.longitudeEL = calendarVM.eventsLocationList.value?.get(position)?.longitude
        calendarVM.titleEL = calendarVM.eventsLocationList.value?.get(position)?.title
        calendarVM.nameEL = calendarVM.eventsLocationList.value?.get(position)?.name
        navController.navigate(R.id.eventsMapFragment)
    }
}