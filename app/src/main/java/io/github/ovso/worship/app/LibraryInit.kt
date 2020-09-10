package io.github.ovso.worship.app

import android.app.Application
import com.google.android.gms.ads.MobileAds
import io.github.ovso.worship.BuildConfig
import timber.log.Timber

class LibraryInit(private val context: Application) {
  init {
    setupTimber()
    setupPrefs()
    setupAds()
  }

  private fun setupAds() {
    //context.getString(R.string.ads_app_id)
    MobileAds.initialize(context)
  }

  private fun setupPrefs() {
//        Koap.bind(app, NavPreferences)
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}
