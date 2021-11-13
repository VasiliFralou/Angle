package by.vfdev.angle.UI.Calendar

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import by.vfdev.angle.R
import by.vfdev.angle.UI.News.NewsDetailFragment
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {

    val fragment = EventsMapFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnShowEventsMap.setOnClickListener {
            fragment.show(requireActivity().supportFragmentManager, "customDialog")
        }
    }
}