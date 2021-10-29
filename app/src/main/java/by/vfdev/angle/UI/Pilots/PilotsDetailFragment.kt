package by.vfdev.angle.UI.Pilots

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import by.vfdev.angle.R
import by.vfdev.angle.UI.MainActivity
import kotlinx.android.synthetic.main.fragment_pilots_detail.*

class PilotsDetailFragment : DialogFragment() {

    private lateinit var pilotsViewModel: PilotsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        pilotsViewModel = ViewModelProvider(activity as MainActivity).get(PilotsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_pilots_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = pilotsViewModel.idPilots!!
        val brd = pilotsViewModel.pilotsList[id].birthday
        val team = pilotsViewModel.pilotsList[id].team
        val desc = pilotsViewModel.pilotsList[id].description

        pilotsNameTV.text = pilotsViewModel.pilotsList[id].name
        pilotsCityTV.text = pilotsViewModel.pilotsList[id].city
        pilotsBirthdayTV.text = "Дата рождения: ${ brd ?: ("-").toString()}"
        pilotsTeamTV.text = "Команда: ${team ?: ("-").toString()}"
        pilotsInstTV.text = "Instagram: ${pilotsViewModel.pilotsList[id].instagram}"
        pilotsDescriptionTV.text = "О пилоте: ${ desc ?: ("-").toString()}"
        pilotsAutoTV.text = "Авто: ${pilotsViewModel.pilotsList[id].auto}"

        showProfileImage(id)
        if (pilotsViewModel.pilotsList[id].photoAuto != null) showPilotsAuto(id)

        btnClosePilotsDetails.setOnClickListener {
            dismiss()
        }
    }

    private fun showProfileImage(id: Int) {
        val imgProfileArray = pilotsViewModel.pilotsList[id].photo
        val bmp = BitmapFactory.decodeByteArray(imgProfileArray, 0, imgProfileArray!!.size)
        pilotsProfileIMG.setImageBitmap(bmp)
    }

    private fun showPilotsAuto(id: Int) {
        val imgAutoArray = pilotsViewModel.pilotsList[id].photoAuto
        val bmp = BitmapFactory.decodeByteArray(imgAutoArray, 0, imgAutoArray!!.size)
        pilotsAutoImg.setImageBitmap(bmp)
    }
}