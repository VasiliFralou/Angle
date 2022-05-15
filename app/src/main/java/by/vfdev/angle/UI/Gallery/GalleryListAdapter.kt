package by.vfdev.angle.UI.Gallery

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Gallery.Gallery
import by.vfdev.angle.Utils.loadImage
import by.vfdev.angle.databinding.ItemGalleryLayoutBinding

class GalleryListAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<GalleryListAdapter.ViewHolder>() {

    private val list: MutableList<Gallery> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val binding by viewBinding (ItemGalleryLayoutBinding::bind)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val  position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_gallery_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.galleryIMG.loadImage(list[position].img)
    }

    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: MutableList<Gallery>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
