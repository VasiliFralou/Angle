package by.vfdev.angle.RemoteModel.News

import com.google.gson.annotations.SerializedName

data class NewsCallBack(
    @SerializedName("News")
    var results: List<News>
)