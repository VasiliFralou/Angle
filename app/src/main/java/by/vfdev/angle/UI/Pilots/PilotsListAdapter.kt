package by.vfdev.angle.UI.Pilots

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Pilots.Pilots
import by.vfdev.angle.Utils.loadImage
import by.vfdev.angle.databinding.ItemPilotsLayoutBinding

class PilotsListAdapter(private val onClick: (pilot: Pilots) -> Unit) :
    RecyclerView.Adapter<PilotsListAdapter.ViewHolder>() {

    private val list: MutableList<Pilots> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding by viewBinding(ItemPilotsLayoutBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_pilots_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.pilotsListTV.text = item.name
        holder.binding.pilotsIMG.loadImage(item.photo)

        holder.itemView.setOnClickListener {
            onClick.invoke(
                list[holder.bindingAdapterPosition]
            )
        }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Pilots>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}

