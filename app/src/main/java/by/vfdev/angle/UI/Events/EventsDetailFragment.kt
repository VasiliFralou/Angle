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
    private val eventsVM: EventsViewModel by activityViewModels()
    private val binding by viewBinding(DetailEventsFragmentBinding::bind)

    private var mMap: GoogleMap? = null

    companion object {
        var mapFragment : SupportMapFragment? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        binding.btnCloseEventsMap.setOnClickListener {
            navController.popBackStack()
        }

        eventsVM.selectEventsLD.observe(viewLifecycleOwner) { events ->

            binding.titleEventMap.text = events.name
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        eventsVM.selectEventsLD.observe(viewLifecycleOwner) { events ->

            val location = LatLng(events.latitude!!,events.longitude!!)

            mMap!!.addMarker(MarkerOptions()
                .position(LatLng(events.latitude, events.longitude))
                .title(events.title))
            mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }
}