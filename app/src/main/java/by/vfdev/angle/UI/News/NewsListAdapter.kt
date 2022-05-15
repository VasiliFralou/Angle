package by.vfdev.angle.UI.News

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.News.News
import by.vfdev.angle.Utils.loadImage
import by.vfdev.angle.databinding.ItemNewsLayoutBinding

class NewsListAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val list: MutableList<News> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val binding by viewBinding (ItemNewsLayoutBinding::bind)

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
        val itemView = inflater.inflate(R.layout.item_news_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.titleTV.text = item.title
        holder.binding.dateTV.text = item.date
        holder.binding.descriptionTV.text = item.description
        holder.binding.postIV.loadImage(item.urlImg)
    }

    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<News>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
