package by.vfdev.angle.UI.Events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Events.Events
import by.vfdev.angle.databinding.ItemEventsLayoutBinding

class EventsListAdapter (private val listener: OnItemClickListener) :
    RecyclerView.Adapter<EventsListAdapter.ViewHolder>() {

    private val list: MutableList<Events> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val binding by viewBinding (ItemEventsLayoutBinding::bind)

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
        val itemView = inflater.inflate(R.layout.item_events_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.nameEventTV.text = item.name
        holder.binding.titleEventTV.text = item.title
        holder.binding.dataEventTV.text = item.day
        holder.binding.cityEventTV.text = item.city
    }

    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Events>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}