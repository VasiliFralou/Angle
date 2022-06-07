package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.GalleryViewModel
import by.vfdev.angle.databinding.ListGalleryFragmentBinding

class GalleryFragment : Fragment(R.layout.list_gallery_fragment) {

    private val navController: NavController by lazy { findNavController() }
    private val galleryVM: GalleryViewModel by activityViewModels()
    private val binding by viewBinding(ListGalleryFragmentBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GalleryListAdapter(
            onClick = {
                galleryVM.onSelectGallery(it)
            }
        )

        binding.GalleryListRV.adapter = adapter
        binding.GalleryListRV.layoutManager = GridLayoutManager(
            requireActivity(), 2)

        galleryVM.galleryLive.observe(viewLifecycleOwner) { list ->
            (binding.GalleryListRV.adapter as GalleryListAdapter).updateData(list)
        }

        galleryVM.onSelectGalleryEvent.observe(viewLifecycleOwner) {
            navController.navigate(R.id.galleryDetailFragment)
        }
    }
}