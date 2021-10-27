package by.vfdev.angle.UI.Pilots

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import by.vfdev.angle.LocalModel.PilotsDatabase
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Pilots
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_pilots_list.*
import kotlinx.android.synthetic.main.item_pilots_layout.*

class PilotsListFragment : Fragment() {

    private lateinit var pilotsViewModel: PilotsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        pilotsViewModel = ViewModelProvider(activity as MainActivity).get(PilotsViewModel::class.java)

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
}