package by.vfdev.angle.UI.Pilots

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import by.vfdev.angle.LocalModel.PilotsDatabase
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Pilots
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_pilots_list.*
import java.util.*

class PilotsListFragment : Fragment() {

    private lateinit var pilotsViewModel: PilotsViewModel
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        pilotsViewModel = ViewModelProvider(activity as MainActivity)[PilotsViewModel::class.java]

        return inflater.inflate(R.layout.fragment_pilots_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(requireActivity(),
            PilotsDatabase::class.java, "Angle.db"
        )
            .createFromAsset("Angle.db")
            .allowMainThreadQueries()
            .build()

        pilotsViewModel.pilotsList = db.pilotsDao().getAll().toMutableList()

        navController = view.findNavController()

        pilotsListRV.layoutManager = GridLayoutManager(activity as MainActivity, 2)
        pilotsListRV.adapter =
            PilotsListAdapter(pilotsViewModel.pilotsList as MutableList<Pilots>, this)

        searchPilot.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchText(query)
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(text: String?): Boolean {
                searchText(text)
                return false
            }
        })
    }

    fun showPilotsDetails(position: Int) {
        pilotsViewModel.idPilots = position
        navController.navigate(R.id.pilotsDetailFragment)
    }

    fun searchText(text: String?) {
        pilotsViewModel.tempArrayList.value?.clear()
        val searchText = text?.lowercase(Locale.getDefault())
        if (!searchText.isNullOrEmpty()) {
            pilotsViewModel.searchText = searchText
            pilotsViewModel.pilotsList.forEach {
                if (it.name.lowercase(Locale.getDefault()).contains(searchText)) {
                    pilotsViewModel.tempArrayList.value?.add(it)
                }
            }
            updateSearchListPilots()
        } else {
            pilotsViewModel.searchText = null
            pilotsListRV.layoutManager = GridLayoutManager(activity as MainActivity, 2)
            pilotsListRV.adapter = PilotsListAdapter(
                pilotsViewModel.pilotsList as MutableList<Pilots>, this@PilotsListFragment)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateSearchListPilots() {
        pilotsListRV.adapter?.notifyDataSetChanged()
        pilotsListRV.layoutManager = GridLayoutManager(activity as MainActivity, 2)
        pilotsListRV.adapter = PilotsListAdapter(
            pilotsViewModel.tempArrayList.value!!, this@PilotsListFragment)
    }
}