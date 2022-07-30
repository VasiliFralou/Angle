package by.vfdev.angle.UI.Events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.Utils.loadImage
import by.vfdev.angle.databinding.ItemEventsLayoutBinding

class EventsListAdapter (private val onClick: (event: Events) -> Unit) :
    RecyclerView.Adapter<EventsListAdapter.ViewHolder>() {

    private val list: MutableList<Events> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding by viewBinding (ItemEventsLayoutBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_events_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.locationIMG.loadImage(item.promo)
        holder.binding.titleEventTV.text = item.title
        holder.binding.nameEventTV.text = item.name
        holder.binding.dataEventTV.text = item.date
        holder.binding.cityEventTV.text = item.location

        holder.itemView.setOnClickListener {
            onClick.invoke(
                list[holder.bindingAdapterPosition]
            )
        }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Events>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}