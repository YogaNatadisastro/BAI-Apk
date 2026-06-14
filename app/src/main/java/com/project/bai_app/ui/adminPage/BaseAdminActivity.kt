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
            val currentDestination = navController.currentDestination?.id
            
            // Avoid re-navigating to the same destination
            if (currentDestination == item.itemId) return@setOnItemSelectedListener false

            val navOptions = androidx.navigation.navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
                launchSingleTop = true
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                restoreState = true
            }

            when (item.itemId) {
                R.id.homeAdminFragment -> {
                    navController.navigate(R.id.homeAdminFragment, null, navOptions)
                    true
                }
                R.id.gad7Fragment -> {
                    navController.navigate(R.id.gad7Fragment, null, navOptions)
                    true
                }
                else -> false
            }
        }
        
        // Synchronize bottom navigation selection with current destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeAdminFragment -> {
                    bind.bottomNavigation.menu.findItem(R.id.homeAdminFragment)?.isChecked = true
                }
                R.id.gad7Fragment, R.id.hadsFragment, R.id.resultFragment -> {
                    bind.bottomNavigation.menu.findItem(R.id.gad7Fragment)?.isChecked = true
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
