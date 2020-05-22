package io.github.ovso.worship.app

import android.app.Application
import com.google.android.gms.ads.MobileAds
import io.github.ovso.worship.BuildConfig
import io.github.ovso.worship.R
import io.github.ovso.worship.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class LibraryInit(private val context: Application) {
  init {
    setupTimber()
    setupPrefs()
    setupDi()
    setupAds()
  }

  private fun setupAds() {
    MobileAds.initialize(context, context.getString(R.string.ads_app_id))
  }

  private fun setupDi() {
    startKoin {
      androidContext(context)
      modules(appModule)
    }
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
