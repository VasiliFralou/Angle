package by.vfdev.angle.UI

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.databinding.FragmentFirstStartDialogBinding

class FirstStartDialogFragment : DialogFragment(R.layout.fragment_first_start_dialog) {

    private val binding by viewBinding(FragmentFirstStartDialogBinding::bind)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = requireActivity().packageManager

        val info = manager.getPackageInfo(requireActivity().packageName, PackageManager.GET_ACTIVITIES)

        binding.titleDialogTV.text = "Что нового в версии ${info.versionName}?"
    }

    override fun onStart() {
        super.onStart()

        val window = dialog!!.window
        // Устанавливаем цвет фона всплывающего окна, чтобы установить размер и положение всплывающего окна
        window?.setBackgroundDrawableResource(by.vfdev.angle.R.color.transparent)
    }
}