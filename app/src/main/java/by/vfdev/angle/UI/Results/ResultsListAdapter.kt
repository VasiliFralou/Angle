package by.vfdev.angle.UI.Results

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Results.Results
import by.vfdev.angle.databinding.ItemResultsLayoutBinding

class ResultsListAdapter(val list: MutableList<Results>) :
    RecyclerView.Adapter<ResultsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding by viewBinding (ItemResultsLayoutBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_results_layout, parent, false)

        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        val sum1 = item.pointsQual1?.let { item.pointsRnd1?.plus(it) }
        val sum2 = item.pointsQual2?.let { item.pointsRnd2?.plus(it) }
        val sum3 = item.pointsQual3?.let { item.pointsRnd3?.plus(it) }
        val sum4 = item.pointsQual4?.let { item.pointsRnd4?.plus(it) }
        val sum5 = item.pointsQual5?.let { item.pointsRnd5?.plus(it) }

        holder.binding.positionPts.text = (position + 1).toString()
        holder.binding.namePilots.text = item.name

        holder.binding.qual1.text = (item.pointsQual1 ?: "-").toString()
        holder.binding.res1.text = (item.pointsRnd1 ?: "-").toString()
        holder.binding.resEvents1.text = (sum1 ?: "-").toString()

        holder.binding.qual2.text = (item.pointsQual2 ?: "-").toString()
        holder.binding.res2.text = (item.pointsRnd2 ?: "-").toString()
        holder.binding.resEvents2.text = (sum2 ?: "-").toString()

        holder.binding.qual3.text = (item.pointsQual3 ?: "-").toString()
        holder.binding.res3.text = (item.pointsRnd3 ?: "-").toString()
        holder.binding.resEvents3.text = (sum3 ?: "-").toString()

        holder.binding.qual4.text = (item.pointsQual4 ?: "-").toString()
        holder.binding.res4.text = (item.pointsRnd4 ?: "-").toString()
        holder.binding.resEvents4.text = (sum4 ?: "-").toString()

        holder.binding.qual5.text = (item.pointsQual5 ?: "-").toString()
        holder.binding.res5.text = (item.pointsRnd5 ?: "-").toString()
        holder.binding.resEvents5.text = (sum5 ?: "-").toString()

        val isExpandable : Boolean = list[position].expandable

        holder.binding.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE


        holder.binding.linearLayout.setOnClickListener {
            val list = list[position]
            list.expandable = !list.expandable
            notifyItemChanged(position)
        }

        holder.binding.pilotsPts.text = "${item.pointResult} pts"
    }

    override fun getItemCount() = list.size
}