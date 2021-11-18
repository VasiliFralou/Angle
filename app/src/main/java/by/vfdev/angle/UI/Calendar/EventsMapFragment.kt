package by.vfdev.angle.UI.Calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.MainViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_events_map.*
import java.lang.Exception

class EventsMapFragment : DialogFragment(), OnMapReadyCallback {

    var mView: MapView? = null
    private lateinit var mMap: GoogleMap

    lateinit var viewModel: MainViewModel

    val minsk = LatLng(53.9042819,27.5439984)

    companion object {
        var mapFragment : SupportMapFragment?=null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

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
        mMap = googleMap
        val size = viewModel.eventsLocationList.value?.size!! - 1
        for(i in 0..size){
            mMap.addMarker(MarkerOptions()
                .position(LatLng(viewModel.eventsLocationList.value?.get(i)?.latitude!!,
                    viewModel.eventsLocationList.value!![i].longitude!!))
                .title(viewModel.eventsLocationList.value!![i].title))
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minsk, 5.5f))
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