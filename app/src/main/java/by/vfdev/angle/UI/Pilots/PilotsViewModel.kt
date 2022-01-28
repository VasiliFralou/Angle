package by.vfdev.angle.UI.Pilots

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.vfdev.angle.RemoteModel.Pilots

class PilotsViewModel : ViewModel() {

    lateinit var pilotsList: List<Pilots>
    var tempArrayList = MutableLiveData<MutableList<Pilots>>(mutableListOf())
    var idPilots: Int? = null
    var searchText: String? = null
}