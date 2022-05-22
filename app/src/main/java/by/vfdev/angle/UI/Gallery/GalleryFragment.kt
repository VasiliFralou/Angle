package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

class GalleryFragment : Fragment(R.layout.list_gallery_fragment),
    GalleryListAdapter.OnItemClickListener {

    private val navController: NavController by lazy { findNavController() }
    private val galleryVM: GalleryViewModel by activityViewModels()
    private val binding by viewBinding(ListGalleryFragmentBinding::bind)

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

        binding.swipeGallery.setOnRefreshListener {
            getList(
                onSuccess = {
                    binding.swipeGallery.isRefreshing = false
                }
            )
        }

        binding.swipeGallery.setColorSchemeResources(R.color.firstColor)
    }

    private fun getList(onSuccess: () -> Unit) {
        galleryVM.getListGallery()
        onSuccess()
    }

    override fun onItemClick(position: Int) {

        galleryVM.linkImages = galleryVM.galleryLive.value?.get(position)?.img

        navController.navigate(R.id.galleryDetailFragment)
    }
}