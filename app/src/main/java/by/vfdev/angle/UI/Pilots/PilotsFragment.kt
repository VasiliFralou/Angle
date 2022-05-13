package by.vfdev.angle.UI.Pilots

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.databinding.FragmentPilotsListBinding

class PilotsFragment : Fragment(R.layout.fragment_pilots_list) {

    private val navController: NavController by lazy { findNavController() }

    private val pilotsVM: PilotsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentPilotsListBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PilotsListAdapter(
            onClick = { pilotsVM.onSelectPilots(it) }
        )
        binding.pilotsListRV.adapter = adapter
        binding.pilotsListRV.layoutManager = GridLayoutManager(
            requireActivity(), 2
        )

        pilotsVM.pilotsSearchList.observe(viewLifecycleOwner) { list ->
            (binding.pilotsListRV.adapter as PilotsListAdapter).updateData(list)
        }

        pilotsVM.onSelectPilotsEvent.observe(viewLifecycleOwner) {
            navController.navigate(R.id.pilotsDetailFragment)
        }



        binding.searchPilot.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextSubmit(query: String?): Boolean {
                pilotsVM.filteredPilots(query)
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(text: String?): Boolean {
                pilotsVM.filteredPilots(text)
                return false
            }
        })
    }

}