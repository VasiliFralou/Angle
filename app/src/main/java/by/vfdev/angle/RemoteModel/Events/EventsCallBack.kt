package by.vfdev.angle.RemoteModel.Events

import com.google.gson.annotations.SerializedName

data class EventsCallBack(
    @SerializedName("Events")
    var results: MutableList<Events>
)
