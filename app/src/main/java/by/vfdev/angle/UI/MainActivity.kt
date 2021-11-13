package by.vfdev.angle.UI

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import by.vfdev.angle.ViewModel.MainViewModel
import by.vfdev.angle.R
import by.vfdev.angle.UI.Calendar.CalendarFragment
import by.vfdev.angle.UI.Pilots.PilotsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {

    lateinit var newsViewModel: MainViewModel
    lateinit var pilotsViewModel: PilotsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        pilotsViewModel = ViewModelProvider(this).get(PilotsViewModel::class.java)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager2>(R.id.pager)
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(newsViewModel.tabNumbers[position])

            if (position == 5) {
                val badge = tab.getOrCreateBadge()
                badge.number = 1
            }

        }.attach()
    }

    internal fun onOpenMap() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CalendarFragment())
            .commitNow()
    }
}