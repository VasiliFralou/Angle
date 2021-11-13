package by.vfdev.angle.UI.Calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.vfdev.angle.R
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import kotlinx.android.synthetic.main.fragment_events_map.*
import java.lang.Exception

class EventsMapFragment : DialogFragment() {

    var mMapView: MapView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView: View = inflater.inflate(R.layout.fragment_events_map, container, false)

        mMapView = rootView.findViewById<View>(R.id.mapView) as MapView
        mMapView!!.onCreate(savedInstanceState)
        mMapView!!.onResume() // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(requireActivity().applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCloseEventsMap.setOnClickListener {
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }
}