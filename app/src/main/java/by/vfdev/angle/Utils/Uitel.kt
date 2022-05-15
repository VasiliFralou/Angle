package by.vfdev.angle.Utils

import android.widget.ImageView
import by.vfdev.angle.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(uri: String?) {

    val option = RequestOptions().placeholder(R.drawable.load)
        .error(R.drawable.load)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(uri)
        .into(this)
}