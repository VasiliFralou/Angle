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


class NewsListAdapter(private val onClick: (news: News) -> Unit) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val list: MutableList<News> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val binding by viewBinding (ItemNewsLayoutBinding::bind)
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
        holder.binding.postIV.loadImage(item.urlImg)
        holder.binding.sourceTV.text = item.source
        holder.itemView.setOnClickListener {
            onClick.invoke(
                list[holder.bindingAdapterPosition]
            )
        }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<News>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
