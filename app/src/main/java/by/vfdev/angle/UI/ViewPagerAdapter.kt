package by.vfdev.angle.UI

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.vfdev.angle.UI.News.NewsFragment
import by.vfdev.angle.UI.Pilots.PilotsListFragment
import by.vfdev.angle.UI.Setting.SettingFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> NewsFragment()
            1 -> PilotsListFragment()
            2 -> SettingFragment()
            else -> Fragment()
        }
    }
}