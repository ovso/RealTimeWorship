package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import io.github.ovso.worship.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDrawer()
        val navController = findNavController(R.id.mainNavFragment)
        NavigationUI.onNavDestinationSelected(nv_main.menu[1], navController)
        NavigationUI.setupWithNavController(nv_main, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.title = destination.label
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        title = getString(R.string.app_name)
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.mainNavFragment), drawer_layout
        )
    }
}
