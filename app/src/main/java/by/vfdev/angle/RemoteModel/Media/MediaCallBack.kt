package by.vfdev.angle.RemoteModel.Media

import com.google.gson.annotations.SerializedName

data class MediaCallBack(
    @SerializedName("Media")
    var results: MutableList<Media>
)