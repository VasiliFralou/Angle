package by.vfdev.angle.UI.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.News.News
import com.bumptech.glide.Glide

class NewsListAdapter(val fragment: NewsFragment) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val list: MutableList<News> = mutableListOf()

    fun updateData(newList: MutableList<News>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTV : TextView = itemView.findViewById(R.id.titleTV)
        var dateTV : TextView =  itemView.findViewById(R.id.dateTV)
        var descriptionTV : TextView = itemView.findViewById(R.id.descriptionTV)
        var postIV: ImageView = itemView.findViewById(R.id.postIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.postnews_content, parent, false)
        val holder = ViewHolder(itemView)

        holder.itemView.setOnClickListener {
            fragment.showNewsDetails(holder.bindingAdapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.titleTV.text = list[position].title
        holder.dateTV.text = list[position].date
        holder.descriptionTV.text = list[position].description
        holder.postIV.let {
            Glide.with(holder.itemView.context)
                .load(list[position].urlImg)
                .into(it)
        }
    }

    override fun getItemCount() = list.size
}
