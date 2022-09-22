package by.vfdev.angle.UI.Media

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Media.Media
import by.vfdev.angle.Utils.loadImage
import by.vfdev.angle.databinding.ItemMediaLayoutBinding

class MediaListAdapter(private val onClick: (media: Media) -> Unit) :
    RecyclerView.Adapter<MediaListAdapter.ViewHolder>() {

    private val list: MutableList<Media> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding by viewBinding(ItemMediaLayoutBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_media_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.mediaIMG.loadImage(item.image)
        holder.binding.titleMediaTV.text = item.title

        holder.binding.icYoutube.isVisible = item.link != null

        holder.itemView.setOnClickListener {
            onClick.invoke(
                list[holder.bindingAdapterPosition]
            )
        }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Media>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
