package by.vfdev.angle.RemoteModel.Results

import com.google.gson.annotations.SerializedName

data class ResultsCallBack(
    @SerializedName("Results")
    var results: MutableList<Results>
)
