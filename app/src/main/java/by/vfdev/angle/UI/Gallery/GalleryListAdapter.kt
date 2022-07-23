package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import by.vfdev.angle.Utils.loadImage
import by.vfdev.angle.databinding.ItemGalleryLayoutBinding
import coil.load
import coil.transform.CircleCropTransformation

class GalleryListAdapter(private val onClick: (gallery: Gallery) -> Unit) :
    RecyclerView.Adapter<GalleryListAdapter.ViewHolder>() {

    private val list: MutableList<Gallery> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding by viewBinding (ItemGalleryLayoutBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_gallery_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.galleryIMG.loadImage(list[position].img)

        holder.itemView.setOnClickListener {
            onClick.invoke(
                list[holder.bindingAdapterPosition]
            )
        }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: MutableList<Gallery>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
