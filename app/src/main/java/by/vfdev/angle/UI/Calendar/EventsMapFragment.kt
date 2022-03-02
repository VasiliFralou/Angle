package by.vfdev.angle.UI.Calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.vfdev.angle.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_events_map.*

class EventsMapFragment : Fragment(), OnMapReadyCallback {

    lateinit var calendarVM: CalendarViewModel
    lateinit var navController: NavController

    private lateinit var mMap: GoogleMap

    companion object {
        var mapFragment : SupportMapFragment?=null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        calendarVM = ViewModelProvider(requireActivity())[CalendarViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_events_map, container, false)

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        titleEventMap.text = calendarVM.nameEL

        btnCloseEventsMap.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(calendarVM.latitudeEL!!,calendarVM.longitudeEL!!)

        mMap = googleMap
        mMap.addMarker(MarkerOptions()
                .position(LatLng(calendarVM.latitudeEL!!, calendarVM.longitudeEL!!))
                .title(calendarVM.titleEL))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }
}