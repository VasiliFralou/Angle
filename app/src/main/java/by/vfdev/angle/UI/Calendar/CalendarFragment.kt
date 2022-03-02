package by.vfdev.angle.UI.Calendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {

    lateinit var calendarVM: CalendarViewModel
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        calendarVM = ViewModelProvider(requireActivity())[CalendarViewModel::class.java]

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        EventsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
        EventsRV.adapter = EventsLocationAdapter(calendarVM.eventsLocationList.value!!, this)

        calendarVM.eventsLocationList.observe(viewLifecycleOwner) {
            EventsRV.adapter?.notifyDataSetChanged()
        }
    }

    fun showEventDetails(position: Int) {
        calendarVM.latitudeEL = calendarVM.eventsLocationList.value?.get(position)?.latitude
        calendarVM.longitudeEL = calendarVM.eventsLocationList.value?.get(position)?.longitude
        calendarVM.titleEL = calendarVM.eventsLocationList.value?.get(position)?.title
        calendarVM.nameEL = calendarVM.eventsLocationList.value?.get(position)?.name
        navController.navigate(R.id.eventsMapFragment)
    }
}