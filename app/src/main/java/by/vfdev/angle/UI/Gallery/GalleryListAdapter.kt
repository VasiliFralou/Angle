package by.vfdev.angle.UI.Gallery

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Gallery
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class GalleryListAdapter(val galleryList: MutableList<Gallery>, val fragment: GalleryFragment) :
    RecyclerView.Adapter<GalleryListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var galleryIMG: ImageView = itemView.findViewById(R.id.galleryIMG)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_gallery_layout, parent, false)
        val holder = ViewHolder(itemView)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.galleryIMG.let {
            Glide.with(holder.itemView.context)
                .load(galleryList[position].img)
                .into(it)
        }
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }
}