package by.vfdev.angle.UI.Calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.EventsLocation

class EventsLocationAdapter (val list: MutableList<EventsLocation>, val fragment: CalendarFragment) :
    RecyclerView.Adapter<EventsLocationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTV : TextView = itemView.findViewById(R.id.nameEventTV)
        var titleTV : TextView = itemView.findViewById(R.id.titleEventTV)
        var dateTV : TextView = itemView.findViewById(R.id.dataEventTV)
        var cityTV : TextView = itemView.findViewById(R.id.cityEventTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.events_content, parent, false)
        val holder = ViewHolder(itemView)

        holder.itemView.setOnClickListener {
            fragment.showEventDetails(holder.adapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nameTV.text = list[position].name
        holder.titleTV.text = list[position].title
        holder.dateTV.text = list[position].day
        holder.cityTV.text = list[position].city
    }

    override fun getItemCount(): Int {
        return list.size
    }

}