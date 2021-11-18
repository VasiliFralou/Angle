package by.vfdev.angle.UI.Calendar

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.UI.MainActivity
import by.vfdev.angle.UI.News.NewsDetailFragment
import by.vfdev.angle.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    val fragment = EventsMapFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EventsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
        EventsRV.adapter = EventsLocationAdapter(viewModel.eventsLocationList.value!!, this)

        btnShowEventsMap.setOnClickListener {
            fragment.show(requireActivity().supportFragmentManager, "customDialog")
        }
    }
}