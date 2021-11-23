package by.vfdev.angle.UI.Pilots

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import by.vfdev.angle.LocalModel.PilotsDatabase
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Pilots
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_pilots_list.*

class PilotsListFragment : Fragment() {

    private lateinit var pilotsViewModel: PilotsViewModel
    val fragment = PilotsDetailFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        pilotsViewModel = ViewModelProvider(activity as MainActivity)[PilotsViewModel::class.java]

        return inflater.inflate(R.layout.fragment_pilots_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(requireActivity(),
            PilotsDatabase::class.java, "Angle.db")
            .createFromAsset("Angle.db")
            .allowMainThreadQueries()
            .build()

        pilotsViewModel.pilotsList = db.pilotsDao().getAll().toMutableList()

        pilotsListRV.layoutManager = GridLayoutManager(activity as MainActivity, 2)
        pilotsListRV.adapter = PilotsListAdapter(pilotsViewModel.pilotsList as MutableList<Pilots>, this)
    }

    fun showPilotsDetails(position: Int) {
        pilotsViewModel.idPilots = pilotsViewModel.pilotsList[position].id
        fragment.show(requireActivity().supportFragmentManager, "customDialog")
    }
}