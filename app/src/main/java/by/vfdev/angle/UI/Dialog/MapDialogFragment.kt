package by.vfdev.angle.UI.Dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.EventsViewModel
import by.vfdev.angle.databinding.FragmentMapDialogBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapDialogFragment : DialogFragment(R.layout.fragment_map_dialog), OnMapReadyCallback {

    private val eventsVM: EventsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentMapDialogBinding::bind)

    private var mMap: GoogleMap? = null

    companion object {
        var mapFragment : SupportMapFragment? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        eventsVM.selectEventsLD.observe(viewLifecycleOwner) { events ->

            val location = LatLng(events.latitude!!,events.longitude!!)

            mMap!!.addMarker(
                MarkerOptions()
                .position(LatLng(events.latitude!!, events.longitude!!))
                .title(events.title))
            mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }
}