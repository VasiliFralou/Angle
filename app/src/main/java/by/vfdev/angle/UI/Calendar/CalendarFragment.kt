package by.vfdev.angle.UI.Calendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.UI.MainActivity
import by.vfdev.angle.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    val fragment = EventsMapFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EventsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
        EventsRV.adapter = EventsLocationAdapter(viewModel.eventsLocationList.value!!, this)

        viewModel.eventsLocationList.observe(viewLifecycleOwner, {
            EventsRV.adapter?.notifyDataSetChanged()
        })
    }

    fun showEventDetails(position: Int) {
        if (fragment.dialog != null && fragment.dialog!!.isShowing&& !fragment.isRemoving) {
        } else {
            //dialog is not showing
            viewModel.latitudeEL = viewModel.eventsLocationList.value?.get(position)?.latitude
            viewModel.longitudeEL = viewModel.eventsLocationList.value?.get(position)?.longitude
            viewModel.titleEL = viewModel.eventsLocationList.value?.get(position)?.title

            fragment.show(requireActivity().supportFragmentManager, "customDialog")
        }
    }
}