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
import by.vfdev.angle.databinding.GalleryFragmentBinding

class GalleryFragment : Fragment(R.layout.gallery_fragment) {

    private val navController: NavController by lazy { findNavController() }

    private val galleryVM: GalleryViewModel by activityViewModels()
    private val binding by viewBinding(GalleryFragmentBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GalleryListAdapter( this)
        binding.GalleryListRV.adapter = adapter
        binding.GalleryListRV.layoutManager = GridLayoutManager(
            requireActivity(), 3)

        galleryVM.galleryLive.observe(viewLifecycleOwner) { list ->
            (binding.GalleryListRV.adapter as GalleryListAdapter).updateData(list)
        }
    }

    fun showImagesDetails(position: Int) {
        galleryVM.linkImages = galleryVM.galleryLive.value?.get(position)?.img
        navController.navigate(R.id.galleryDetailFragment)
    }
}