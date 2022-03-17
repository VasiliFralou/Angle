package by.vfdev.angle.RemoteModel.Pilots

import com.google.gson.annotations.SerializedName

data class PilotsCallBack(
    @SerializedName("Pilots")
    var results: MutableList<Pilots>
)