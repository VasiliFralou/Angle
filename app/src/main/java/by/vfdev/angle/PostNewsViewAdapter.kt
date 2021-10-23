package by.vfdev.angle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.ValueEventListener

class PostNewsViewAdapter(private val values: MutableList<News>, val fragment: ValueEventListener) :
    RecyclerView.Adapter<PostNewsViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.postnews_content, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.findViewById<TextView>(R.id.titleTV).text = values[position].title
        holder.itemView.findViewById<TextView>(R.id.dateTV).text = values[position].date
        holder.itemView.findViewById<TextView>(R.id.descriptionTV).text = values[position].description
        holder.itemView.findViewById<ImageView>(R.id.postIV)?.let {
            Glide.with(holder.itemView.context)
                .load(values[position].urlImg)
                .into(it)
        }

//        holder.itemView.setOnClickListener { v ->
//            val intent = Intent(v.context, VideogameDetail::class.java).apply {
//                putExtra("key", videogame.key)
//            }
//            v.context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return values.size
    }
}
