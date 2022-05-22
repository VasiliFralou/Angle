package by.vfdev.angle.UI.Events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.EventsViewModel
import by.vfdev.angle.databinding.DetailEventsFragmentBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class EventsDetailFragment : Fragment(R.layout.detail_events_fragment), OnMapReadyCallback {

    private val navController: NavController by lazy { findNavController() }

    private var mMap: GoogleMap? = null

    private val eventsVM: EventsViewModel by activityViewModels()
    private val binding by viewBinding(DetailEventsFragmentBinding::bind)

    companion object {
        var mapFragment : SupportMapFragment? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        binding.titleEventMap.text = eventsVM.nameEL

        binding.btnCloseEventsMap.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(eventsVM.latitudeEL!!,eventsVM.longitudeEL!!)

        mMap = googleMap

        mMap!!.addMarker(MarkerOptions()
                .position(LatLng(eventsVM.latitudeEL!!, eventsVM.longitudeEL!!))
                .title(eventsVM.titleEL))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }
}