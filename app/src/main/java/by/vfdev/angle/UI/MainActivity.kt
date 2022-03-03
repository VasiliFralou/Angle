package by.vfdev.angle.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import by.vfdev.angle.R
import by.vfdev.angle.UI.Calendar.CalendarViewModel
import by.vfdev.angle.UI.Calendar.CalendarViewModelFactory
import by.vfdev.angle.UI.Pilots.PilotsViewModel
import by.vfdev.angle.ViewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: MainViewModel
    private lateinit var pilotsViewModel: PilotsViewModel
    lateinit var calendarVM: CalendarViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject lateinit var factory: CalendarViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment

        navController = navHostFragment.navController

        newsViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        pilotsViewModel = ViewModelProvider(this)[PilotsViewModel::class.java]
        calendarVM = ViewModelProvider(this, factory)[CalendarViewModel::class.java]

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setupWithNavController(navController)

        calendarVM.getListEvent()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.newsFragment, R.id.calendarFragment, R.id.galleryFragment, R.id.pilotsListFragment)
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}