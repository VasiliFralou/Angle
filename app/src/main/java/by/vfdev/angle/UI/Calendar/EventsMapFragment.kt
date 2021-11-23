package by.vfdev.angle.UI.Calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.MainViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_events_map.*

class EventsMapFragment : DialogFragment(), OnMapReadyCallback {

    lateinit var viewModel: MainViewModel

    var mView: MapView? = null
    private lateinit var mMap: GoogleMap

    companion object {
        var mapFragment : SupportMapFragment?=null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_events_map, container, false)

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCloseEventsMap.setOnClickListener {
            dismiss()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(viewModel.latitudeEL!!,viewModel.longitudeEL!!)

        mMap = googleMap
        mMap.addMarker(MarkerOptions()
                .position(LatLng(viewModel.latitudeEL!!, viewModel.longitudeEL!!))
                .title(viewModel.titleEL))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

    override fun onResume() {
        super.onResume()
        mView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mView?.onLowMemory()
    }
}