package by.vfdev.angle.UI.Events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.databinding.FragmentEventsMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class EventsDetailFragment : Fragment(R.layout.fragment_events_map), OnMapReadyCallback {

    lateinit var navController: NavController

    lateinit var eventsVM: EventsViewModel
    private lateinit var mMap: GoogleMap
    private val binding by viewBinding(FragmentEventsMapBinding::bind)

    companion object {
        var mapFragment : SupportMapFragment? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsVM = ViewModelProvider(requireActivity())[EventsViewModel::class.java]
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        navController = view.findNavController()

        binding.titleEventMap.text = eventsVM.nameEL

        binding.btnCloseEventsMap.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(eventsVM.latitudeEL!!,eventsVM.longitudeEL!!)

        mMap = googleMap
        mMap.addMarker(MarkerOptions()
                .position(LatLng(eventsVM.latitudeEL!!, eventsVM.longitudeEL!!))
                .title(eventsVM.titleEL))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }
}