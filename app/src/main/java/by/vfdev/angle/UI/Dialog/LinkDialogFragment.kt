package by.vfdev.angle.UI.Dialog

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.ViewModel.NewsViewModel
import by.vfdev.angle.databinding.FragmentLinkDialogBinding

class LinkDialogFragment : DialogFragment(R.layout.fragment_link_dialog) {

    private val newsVM: NewsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentLinkDialogBinding::bind)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGO.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(newsVM.selectNewsLD.value?.urlPost))
            startActivity(browserIntent)
            dismiss()
        }

        binding.btnCancel.setOnClickListener{
            dismiss()
        }

        binding.titleDialogLinkTV.text = "Читать подробнее новость на: \n\n ${newsVM.selectNewsLD.value?.urlPost} ?"
    }

    override fun onStart() {
        super.onStart()

        val window = dialog!!.window
        window?.setBackgroundDrawableResource(R.color.transparent)
    }
}