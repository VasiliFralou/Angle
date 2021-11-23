package by.vfdev.angle.UI

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.vfdev.angle.UI.Calendar.CalendarFragment
import by.vfdev.angle.UI.Gallery.GalleryFragment
import by.vfdev.angle.UI.News.NewsFragment
import by.vfdev.angle.UI.Pilots.PilotsListFragment
import by.vfdev.angle.UI.Setting.SettingFragment
import by.vfdev.angle.UI.Video.VideoFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> NewsFragment()
            1 -> CalendarFragment()
            2 -> GalleryFragment()
            // 3 -> VideoFragment()
            3 -> PilotsListFragment()
            // 4 -> SettingFragment()
            else -> Fragment()
        }
    }
}