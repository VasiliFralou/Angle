package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.UI.MainActivity
import by.vfdev.angle.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_gallery.*


class GalleryFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel
    val fragment = GalleryDetailFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainViewModel = ViewModelProvider(activity as MainActivity)[MainViewModel::class.java]

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GalleryListRV.layoutManager = GridLayoutManager(activity as MainActivity, 2)
        GalleryListRV.adapter = GalleryListAdapter(mainViewModel.galleryList.value!!, this)

        mainViewModel.galleryList.observe(viewLifecycleOwner, {
            GalleryListRV.adapter?.notifyDataSetChanged()
        })
    }

    fun showImagesDetails(position: Int) {
        mainViewModel.linkImages = mainViewModel.galleryList.value?.get(position)?.img
        fragment.show(requireActivity().supportFragmentManager, "customDialog")
    }
}