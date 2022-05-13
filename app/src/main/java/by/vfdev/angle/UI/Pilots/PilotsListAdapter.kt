package by.vfdev.angle.UI.Pilots

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Pilots.Pilots
import by.vfdev.angle.databinding.ItemPilotsLayoutBinding
import com.bumptech.glide.Glide

class PilotsListAdapter(
    private val onClick: (pilot: Pilots) -> Unit) :
    RecyclerView.Adapter<PilotsListAdapter.ViewHolder>() {

    private val list: MutableList<Pilots> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Pilots>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding by viewBinding(ItemPilotsLayoutBinding::bind)

        fun image(pilots: Pilots) {
            with(itemView) {
                Glide.with(this)
                    .load(pilots.photo)
                    .into(binding.pilotsIMG)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_pilots_layout, parent, false)
        val holder = ViewHolder(itemView)

        holder.itemView.setOnClickListener {
            onClick.invoke(
                list[holder.bindingAdapterPosition]
            )
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.pilotsListTV.text = list[position].name
        val imageList = list[position]
        holder.image(imageList)
    }

    override fun getItemCount() = list.size
}

