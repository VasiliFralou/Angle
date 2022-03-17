package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import com.bumptech.glide.Glide

class GalleryListAdapter(val fragment: GalleryFragment) :
    RecyclerView.Adapter<GalleryListAdapter.ViewHolder>() {

    private val list: MutableList<Gallery> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: MutableList<Gallery>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var galleryIMG: ImageView = itemView.findViewById(R.id.galleryIMG)

        fun image(gallery: Gallery) {
            with(itemView) {
                Glide.with(this)
                    .load(gallery.img)
                    .into(galleryIMG)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_gallery_layout, parent, false)
        val holder = ViewHolder(itemView)

        holder.itemView.setOnClickListener {
            fragment.showImagesDetails(holder.bindingAdapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val galleryList = list[position]
        holder.image(galleryList)
    }

    override fun getItemCount() = list.size
}
