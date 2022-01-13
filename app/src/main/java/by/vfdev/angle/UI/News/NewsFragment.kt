package by.vfdev.angle.UI.News

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.newsList.value?.isEmpty() == true) {
            viewModel.initialize()
        }

        PostNewsRV.layoutManager = GridLayoutManager(activity as MainActivity, 1)
        PostNewsRV.adapter = PostNewsAdapter(viewModel.newsList.value!!, this)

        viewModel.newsList.observe(viewLifecycleOwner, {
            PostNewsRV.adapter?.notifyDataSetChanged()
            Log.e("!!!News", viewModel.newsList.value!!.size.toString())
        })
    }

    fun showNewsDetails(position: Int) {
        if (fragment.dialog != null && fragment.dialog!!.isShowing&& !fragment.isRemoving) {
        } else {
            //dialog is not showing
            viewModel.news = viewModel.newsList.value?.get(position)?.urlPost
            fragment.show(requireActivity().supportFragmentManager, "customDialog")
        }
    }
}