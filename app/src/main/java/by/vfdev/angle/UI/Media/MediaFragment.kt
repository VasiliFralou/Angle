package by.vfdev.angle.UI.Media

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.MediaViewModel
import by.vfdev.angle.databinding.ListMediaFragmentBinding

class MediaFragment : Fragment(R.layout.list_media_fragment) {

    private val navController: NavController by lazy { findNavController() }
    private val mediaVM: MediaViewModel by activityViewModels()
    private val binding by viewBinding(ListMediaFragmentBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MediaListAdapter(
            onClick = {
                if (it.link != null) {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(it.link))
                    startActivity(browserIntent)
                } else {
                    mediaVM.onSelectMedia(it)
                    Log.e("!!!", "GGG")
                }
            }
        )

        binding.GalleryListRV.adapter = adapter
        binding.GalleryListRV.layoutManager = LinearLayoutManager(requireActivity())

        mediaVM.searchListMedia.observe(viewLifecycleOwner) { list ->
            (binding.GalleryListRV.adapter as MediaListAdapter).updateData(list)
        }

        mediaVM.onSelectMediaEvent.observe(viewLifecycleOwner) {
            navController.navigate(R.id.galleryDetailFragment)
        }

        binding.searchMedia.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextSubmit(query: String?): Boolean {
                mediaVM.filteredMedia(query)
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(text: String?): Boolean {
                mediaVM.filteredMedia(text)
                return false
            }
        })
    }
}