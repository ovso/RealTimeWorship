package io.github.ovso.worship.extensions

import android.app.Activity
import android.util.DisplayMetrics
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import io.github.ovso.worship.R

fun Activity.adaptiveBannerAdSize(): AdSize {
  val display = windowManager.defaultDisplay
  val outMetrics = DisplayMetrics()
  display.getMetrics(outMetrics)

  val density = outMetrics.density

  var adWidthPixels = 0f
  if (adWidthPixels == 0f) {
    adWidthPixels = outMetrics.widthPixels.toFloat()
  }

  val adWidth = (adWidthPixels / density).toInt()
  return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
}

fun Fragment.adaptiveBannerAdSize(): AdSize {
  val context = requireActivity()
  val display = context.windowManager.defaultDisplay
  val outMetrics = DisplayMetrics()
  display.getMetrics(outMetrics)

  val density = outMetrics.density

  var adWidthPixels = 0f
  if (adWidthPixels == 0f) {
    adWidthPixels = outMetrics.widthPixels.toFloat()
  }

  val adWidth = (adWidthPixels / density).toInt()
  return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth)
}

fun Activity.loadAdaptiveBanner(container: ViewGroup, unitId: String) {
  val adView = AdView(container.context)
  container.addView(adView)

  fun load() {
    adView.adUnitId = unitId
    adView.adSize = adaptiveBannerAdSize()
    val adRequest = AdRequest.Builder().build()
    adView.loadAd(adRequest)
  }

  load()
}

fun Activity.loadAdaptiveBanner(container: ViewGroup) {
  val adView = AdView(container.context)
  container.addView(adView)

  fun load() {
    adView.adUnitId = container.resources.getString(R.string.ads_banner_unit_id)
    adView.adSize = adaptiveBannerAdSize()
    val adRequest = AdRequest.Builder().build()
    adView.loadAd(adRequest)
  }

  load()
}

fun Activity.hideBanner() {
  findViewById<ViewGroup>(R.id.ads_container)?.isVisible = false
}

fun Activity.showBanner() {
  findViewById<ViewGroup>(R.id.ads_container)?.isVisible = true
}

fun Fragment.hideBanner() {
  activity?.findViewById<ViewGroup>(R.id.ads_container)?.isVisible = false
}

fun Fragment.showBanner() {
  activity?.findViewById<ViewGroup>(R.id.ads_container)?.isVisible = true
}

fun Fragment.loadAdaptiveBanner(container: ViewGroup, unitId: String) {
  val adView = AdView(container.context)
  container.addView(adView)

  fun load() {
    adView.adUnitId = unitId
    adView.adSize = adaptiveBannerAdSize()
    val adRequest = AdRequest.Builder().build()
    adView.loadAd(adRequest)
  }

  load()
}
