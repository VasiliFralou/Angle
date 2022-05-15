package by.vfdev.angle.UI

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val newsVM: NewsViewModel by viewModels()
    private val eventsVM: EventsViewModel by viewModels()
    private val galleryVM: GalleryViewModel by viewModels()
    private val pilotsVM: PilotsViewModel by viewModels()

    private val navController by lazy {
        supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        )!!.findNavController()
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    var sharedPreferences: SharedPreferences? = null
    private var sharedEditor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (savedInstanceState == null) {
//            sharedPreferences = getPreferences(MODE_PRIVATE)
//            sharedEditor = sharedPreferences!!.edit()
//            if (isFirstStart()) {
//                Toast.makeText(applicationContext, "Первый запуск приложения", Toast.LENGTH_SHORT).show()
//                val intent = intent
//                finish()
//                startActivity(intent)
//            }
//        }

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

    private fun isFirstStart(): Boolean {
        return if (sharedPreferences!!.getBoolean("firstTime", true)) {
            sharedEditor!!.putBoolean("firstTime", false)
            sharedEditor!!.apply()
            true
        } else {
            false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id != R.id.newsFragment) navController.popBackStack()
        else super.onBackPressed()
    }
}