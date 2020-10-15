package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.worship.R
import io.github.ovso.worship.extensions.loadAdaptiveBanner
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    val navView: BottomNavigationView = findViewById(R.id.bnv_main)
    val navController = findNavController(R.id.nav_host_fragment)
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    val appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.navigation_home,
        R.id.navigation_bookmark,
        R.id.navigation_history,
        R.id.navigation_story
      )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)
    loadAdaptiveBanner(ads_container)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    onBackPressed()
    return super.onOptionsItemSelected(item)
  }

/*
  override fun onBackPressed() {
    Toast.makeText(this, "show dialog", Toast.LENGTH_SHORT).show()
    val templateView =
      LayoutInflater.from(this).inflate(R.layout.dialog_native_ads, null, false) as TemplateView
    val builder = AdLoader.Builder(this, getString(R.string.ads_native_unit_id)).apply {
      forUnifiedNativeAd {
        templateView.setNativeAd(it)
        Toast.makeText(this@MainActivity, "set native ad", Toast.LENGTH_SHORT).show()
      }
    }
    val adLoader = builder.build()
    val adRequest = AdRequest.Builder().build()
    adLoader.loadAd(adRequest)
    AlertDialog.Builder(this).setView(templateView)
      .setTitle(R.string.dialog_title_exit)
      .setPositiveButton(R.string.dialog_exit_yes_btn) { dialog, _ ->
        dialog.dismiss()
      }
      .setNeutralButton(R.string.dialog_exit_no_btn) { dialog, _ ->
        dialog.dismiss()
      }
      .show()
  }
*/

}
