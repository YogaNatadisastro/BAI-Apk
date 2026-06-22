package com.project.bai_app.ui.adminPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.project.bai_app.R
import com.project.bai_app.databinding.ActivityBaseAdminBinding


class BaseAdminActivity : AppCompatActivity() {

    private lateinit var bind: ActivityBaseAdminBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityBaseAdminBinding.inflate(layoutInflater)
        setContentView(bind.root)

        setupWindowInsets()
        setupNavigation()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(bind.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        bind.bottomNavigation.setOnItemSelectedListener { item ->
            val currentDes = navController.currentDestination?.id
            val navOptBuilder = androidx.navigation.navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
                launchSingleTop = true
            }

            when (item.itemId) {
                R.id.homeAdminFragment -> {
                    if (currentDes != R.id.homeAdminFragment) {
                        navController.navigate(R.id.homeAdminFragment, null, navOptBuilder)
                    }
                    true
                }
                R.id.assessmentFragment -> {
                    if (currentDes != R.id.assessmentFragment) {
                        navController.navigate(R.id.assessmentFragment, null, navOptBuilder)
                    }
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
