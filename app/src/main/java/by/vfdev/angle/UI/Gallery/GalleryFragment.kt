package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_gallery.*


class GalleryFragment : Fragment() {

    lateinit var navController: NavController
    private val galleryVM: GalleryViewModel by activityViewModels()
    private val gallery = mutableListOf<Gallery>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        galleryVM.getListGallery()

        galleryVM.galleryLive.observe(activity as MainActivity) {
            gallery.clear()
            gallery.addAll(it)
            GalleryListRV.adapter?.notifyDataSetChanged()
        }

        val adapter = GalleryListAdapter(gallery, this)
        GalleryListRV.adapter = adapter
        GalleryListRV.layoutManager = GridLayoutManager(activity as MainActivity, 3)
    }

    fun showImagesDetails(position: Int) {
        galleryVM.linkImages = galleryVM.galleryList.value?.get(position)?.img
        navController.navigate(R.id.galleryDetailFragment)
    }
}