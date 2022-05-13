package by.vfdev.angle.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import by.vfdev.angle.R
import by.vfdev.angle.UI.Events.EventsViewModel
import by.vfdev.angle.UI.Gallery.GalleryViewModel
import by.vfdev.angle.UI.News.NewsViewModel
import by.vfdev.angle.UI.Pilots.PilotsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var newsVM: NewsViewModel
    private lateinit var eventsVM: EventsViewModel
    private lateinit var galleryVM: GalleryViewModel
    private lateinit var pilotsVM: PilotsViewModel

    private val navController by lazy {
        supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        )!!.findNavController()
    }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        newsVM = ViewModelProvider(this)[NewsViewModel::class.java]
        eventsVM = ViewModelProvider(this)[EventsViewModel::class.java]
        galleryVM = ViewModelProvider(this)[GalleryViewModel::class.java]
        pilotsVM = ViewModelProvider(this)[PilotsViewModel::class.java]

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setupWithNavController(navController)

        newsVM.getListNews()
        eventsVM.getListEvents()
        galleryVM.getListGallery()
        pilotsVM.getListPilots()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.newsFragment,
                R.id.calendarFragment,
                R.id.galleryFragment,
                R.id.pilotsListFragment
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id != R.id.newsFragment) navController.popBackStack()
        else super.onBackPressed()
    }
}