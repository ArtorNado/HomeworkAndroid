package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication.ui.gallery.GalleryFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.slideshow.SlideshowFragment
import com.example.myapplication.ui.tools.GoDataEntryFragment
import com.example.myapplication.ui.userProfile.DataEntryFragment
import com.example.myapplication.ui.userProfile.UserProfileFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_nd.*
import kotlinx.android.synthetic.main.app_bar_nd.*

class NDActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nd)
        setSupportActionBar(findViewById(R.id.toolbar))
        val actionBar = supportActionBar
        actionBar?.title = "Hello Toolbar"
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }
        val navView: NavigationView = findViewById(R.id.nav_view)

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        goHomeFragment()
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> if (!it.isChecked) goHomeFragment()
                R.id.nav_gallery -> if (!it.isChecked) goGalleryFragment()
                R.id.nav_slideshow -> if (!it.isChecked) goSlideshowFragment()
                R.id.nav_entry -> if (!it.isChecked) goDataEntryFragment()
            }
            back()
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun goHomeFragment() {
        supportFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment, HomeFragment.newInstance(), "tag")
                addToBackStack(HomeFragment::class.java.name)
                commit()
            }
        }
    }

    private fun goSlideshowFragment() {
        supportFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment, SlideshowFragment.newInstance(), "tag")
                addToBackStack(SlideshowFragment::class.java.name)
                commit()
            }
        }
    }

    private fun goDataEntryFragment() {
        supportFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment, GoDataEntryFragment.newInstance(), "tag")
                addToBackStack(GoDataEntryFragment::class.java.name)
                commit()
            }
        }
    }

    private fun goGalleryFragment() {
        supportFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment, GalleryFragment.newInstance(), "tag")
                addToBackStack(GalleryFragment::class.java.name)
                commit()
            }
        }
    }

    private fun back() {
        supportFragmentManager.apply {
            addOnBackStackChangedListener {
                println(findFragmentByTag("tag"))
                when (findFragmentByTag("tag")) {
                    is GoDataEntryFragment ->
                        nav_view.menu.getItem(3).apply {
                            toolbar.title = "Go Data Entry Fragment"
                        }
                    is HomeFragment ->
                        nav_view.menu.getItem(0).apply {
                            toolbar.title = "Home Fragment"
                        }
                    is GalleryFragment ->
                        nav_view.menu.getItem(1).apply {
                            toolbar.title = "Gallery Fragment"
                        }
                    is SlideshowFragment ->
                        nav_view.menu.getItem(2).apply {
                            toolbar.title = "Slideshow Fragment"
                        }
                    is DataEntryFragment ->
                        toolbar.title = "Data Entry Fragment"
                    is UserProfileFragment ->
                        toolbar.title = "Profile Fragment"
                }
            }
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        when {
            drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
            fragmentManager.backStackEntryCount > 0 -> fragmentManager.popBackStack()
            else -> super.onBackPressed()
        }
    }
}
