package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import io.github.ovso.worship.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main),
  NavController.OnDestinationChangedListener {
  private val navController by lazy { findNavController(R.id.mainNavFragment) }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupToolbar()
    setupNavigationDrawer(toolbar)
  }

  private fun setupNavigationDrawer(toolbar: Toolbar) {
    NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
    nv_main.setupWithNavController(navController)
  }

  private fun setupToolbar() {
    setSupportActionBar(toolbar)
  }

  override fun onSupportNavigateUp(): Boolean {
    return NavigationUI.navigateUp(
      Navigation.findNavController(this, R.id.mainNavFragment), drawer_layout
    )
  }

  override fun onDestinationChanged(
    controller: NavController,
    destination: NavDestination,
    arguments: Bundle?
  ) {
    toolbar.title = destination.label
  }
}
