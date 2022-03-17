package by.vfdev.angle.UI.Video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.angle.R
import by.vfdev.angle.databinding.FragmentVideoBinding

class VideoFragment : Fragment(R.layout.fragment_video) {

    private val binding by viewBinding(FragmentVideoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}