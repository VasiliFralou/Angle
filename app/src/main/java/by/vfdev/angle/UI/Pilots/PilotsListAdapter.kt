package by.vfdev.angle.UI.Pilots

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Pilots

class PilotsListAdapter (val pilotsList: MutableList<Pilots>, val fragment: PilotsListFragment) :
    RecyclerView.Adapter<PilotsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var pilotsIMG : ImageView = itemView.findViewById(R.id.pilotsIMG)
        var pilotsListTV : TextView = itemView.findViewById(R.id.pilotsListTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_pilots_layout, parent, false)
        val holder = ViewHolder(itemView)

        holder.itemView.setOnClickListener {
            fragment.showPilotsDetails(holder.adapterPosition)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imgArray = pilotsList[position].photo
        val bmp = BitmapFactory.decodeByteArray(imgArray, 0, imgArray!!.size)

        holder.pilotsListTV.text = pilotsList[position].name
        holder.pilotsIMG.setImageBitmap(bmp)
    }

    override fun getItemCount() = pilotsList.size
}