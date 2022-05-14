package by.vfdev.angle.UI.Events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Events.Events

class EventsListAdapter (val fragment: EventsFragment) :
    RecyclerView.Adapter<EventsListAdapter.ViewHolder>() {

    private val list: MutableList<Events> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: MutableList<Events>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTV : TextView = itemView.findViewById(R.id.nameEventTV)
        var titleTV : TextView = itemView.findViewById(R.id.titleEventTV)
        var dateTV : TextView = itemView.findViewById(R.id.dataEventTV)
        var cityTV : TextView = itemView.findViewById(R.id.cityEventTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_events_layout, parent, false)
        val holder = ViewHolder(itemView)

        holder.itemView.setOnClickListener {
            fragment.showEventDetails(holder.bindingAdapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTV.text = list[position].name
        holder.titleTV.text = list[position].title
        holder.dateTV.text = list[position].day
        holder.cityTV.text = list[position].city
    }

    override fun getItemCount() = list.size

}