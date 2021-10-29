package by.vfdev.angle.UI.News

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.vfdev.angle.R
import by.vfdev.angle.UI.MainActivity
import by.vfdev.angle.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    val fragment = NewsDetailFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initialize()

        PostNewsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
        PostNewsRV.adapter = PostNewsAdapter(viewModel.newsList.value!!, this)

        viewModel.newsList.observe(viewLifecycleOwner, {
            PostNewsRV.adapter?.notifyDataSetChanged()
        })
    }

    fun showNewsDetails(position: Int) {
        viewModel.news = viewModel.newsList.value?.get(position)?.urlPost
        fragment.show(requireActivity().supportFragmentManager, "customDialog")
    }
}