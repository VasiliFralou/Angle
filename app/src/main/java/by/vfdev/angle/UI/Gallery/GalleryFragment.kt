package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_news.*

class GalleryFragment : Fragment() {

    lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        galleryViewModel = ViewModelProvider(activity as MainActivity)[GalleryViewModel::class.java]

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (galleryViewModel.galleryList.value?.isEmpty() == true) {
            galleryViewModel.initialize()
        }

        GalleryListRV.layoutManager = GridLayoutManager(activity as MainActivity, 2)
        GalleryListRV.adapter = GalleryListAdapter(galleryViewModel.galleryList.value!!, this)

        galleryViewModel.galleryList.observe(viewLifecycleOwner, {
            GalleryListRV.adapter?.notifyDataSetChanged()
            Log.e("!!!Gallery", galleryViewModel.galleryList.value!!.size.toString())
        })
    }
}