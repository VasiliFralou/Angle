package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_gallery.*


class GalleryFragment : Fragment() {

    lateinit var galleryVM: GalleryViewModel
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        galleryVM = ViewModelProvider(activity as MainActivity)[GalleryViewModel::class.java]

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        GalleryListRV.layoutManager = GridLayoutManager(requireActivity(),3)
        GalleryListRV.adapter = GalleryListAdapter(galleryVM.galleryList.value!!, this)

        galleryVM.galleryList.observe(viewLifecycleOwner) {
            GalleryListRV.adapter?.notifyDataSetChanged()
        }
    }

    fun showImagesDetails(position: Int) {
        galleryVM.linkImages = galleryVM.galleryList.value?.get(position)?.img
        navController.navigate(R.id.galleryDetailFragment)
    }
}