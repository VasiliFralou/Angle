package by.vfdev.angle.RemoteModel.Gallery

import com.google.gson.annotations.SerializedName

data class GalleryCallBack(
    @SerializedName("Images")
    var results: MutableList<Gallery>
)