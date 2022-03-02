package by.vfdev.angle.UI.Pilots

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.vfdev.angle.R
import by.vfdev.angle.RemoteModel.Pilots
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_pilots_detail.*

class PilotsDetailFragment : Fragment() {

    private lateinit var pilotsViewModel: PilotsViewModel
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        pilotsViewModel = ViewModelProvider(activity as MainActivity).get(PilotsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_pilots_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        if (pilotsViewModel.tempArrayList.value?.size!! > 0) {
            viewPilotInfo(pilotsViewModel.tempArrayList.value!!)
        } else {
            viewPilotInfo(pilotsViewModel.pilotsList)
        }
        btnClosePilotsDetails.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun showProfileImage(id: Int, list: List<Pilots>) {
        val imgProfileArray = list[id].photo
        val bmp = BitmapFactory.decodeByteArray(imgProfileArray, 0, imgProfileArray!!.size)
        pilotsProfileIMG.setImageBitmap(bmp)
    }

    private fun showPilotsAuto(id: Int, list: List<Pilots>) {
        val imgAutoArray = list[id].photoAuto
        val bmp = BitmapFactory.decodeByteArray(imgAutoArray, 0, imgAutoArray!!.size)
        pilotsAutoImg.setImageBitmap(bmp)
    }

    @SuppressLint("SetTextI18n")
    private fun viewPilotInfo(list: List<Pilots>) {
        val id = pilotsViewModel.idPilots!!
        val brd = list[id].birthday
        val team = list[id].team
        val desc = list[id].description

        pilotsNameTV.text = list[id].name
        pilotsCityTV.text = "Город: ${list[id].city}"
        pilotsBirthdayTV.text = brd ?: ("-").toString()
        pilotsTeamTV.text = "Команда: ${team ?: ("-").toString()}"
        pilotsInstTV.text = "Instagram: ${list[id].instagram}"
        pilotsDescriptionTV.text = desc ?: ("-").toString()
        pilotsAutoTV.text = "Авто: ${list[id].auto}"

        showProfileImage(id, list)
        if (list[id].photoAuto != null) showPilotsAuto(id, list)
    }
}