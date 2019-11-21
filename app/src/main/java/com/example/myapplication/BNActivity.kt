package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.dashboard.DashboardFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BNActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bn)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_dashboard -> if (!it.isChecked) goDashboardFragment()
                R.id.navigation_home -> if (!it.isChecked) goHomeFragment()
                R.id.navigation_notifications -> if (!it.isChecked) goNotificationsFragment()
            }
            true
        }
    }

    private fun goDashboardFragment() {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.nav_host_fragment, DashboardFragment.newInstance(), "tag")
                addToBackStack(DashboardFragment::class.java.name)
                commit()
            }
        }
    }

    private fun goHomeFragment() {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.nav_host_fragment, HomeFragment.newInstance(), "tag")
                addToBackStack(HomeFragment::class.java.name)
                commit()
            }
        }
    }

    private fun goNotificationsFragment() {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.nav_host_fragment, NotificationsFragment.newInstance(), "tag")
                addToBackStack(NotificationsFragment::class.java.name)
                commit()
            }
        }
    }

}
