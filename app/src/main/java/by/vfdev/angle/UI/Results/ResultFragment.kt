package by.vfdev.angle.UI.Results

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Results.Results
import by.vfdev.angle.ViewModel.ResultsViewModel
import by.vfdev.angle.databinding.ListResultFragmentBinding

class ResultFragment : Fragment(R.layout.list_result_fragment) {

    private val resultsVM: ResultsViewModel by activityViewModels()
    private val binding by viewBinding(ListResultFragmentBinding::bind)
    private val listResults = mutableListOf<Results>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ResultsListAdapter(listResults)
        binding.resultsListRV.adapter = adapter
        binding.resultsListRV.layoutManager = LinearLayoutManager(requireActivity())

        resultsVM.resultsLive.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        binding.swipeResults.setOnRefreshListener {
            getList(
                onSuccess = {
                    binding.swipeResults.isRefreshing = false
                }
            )
        }
        binding.swipeResults.setColorSchemeResources(R.color.firstColor)
    }

    private fun getList(onSuccess: () -> Unit) {
        resultsVM.getListResults()
        onSuccess()
    }
}