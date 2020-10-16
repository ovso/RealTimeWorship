package io.github.ovso.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import io.github.ovso.view.databinding.DialogNativeAds2Binding
import io.github.ovso.view.databinding.DialogNativeAdsBinding

class NativeAdsDialog2(context: Context) : AlertDialog.Builder(context) {

  private val binding by lazy {
    DialogNativeAds2Binding.inflate(LayoutInflater.from(context))
  }

  private var unitId: String? = null
  fun setUnitId(unitId: String?): AlertDialog.Builder {
    this.unitId = unitId
    setView(binding.root)
//    binding.root.isRefreshing = true
    binding.progressBar.isVisible = true
    val templateView = binding.templateView
    val builder = AdLoader.Builder(context, unitId).apply {
      forUnifiedNativeAd {
        templateView.setNativeAd(it)
        binding.progressBar.isVisible = false
      }
    }
    val adLoader = builder.build()
    val adRequest = AdRequest.Builder().build()
    adLoader.loadAd(adRequest)
    return this
  }

  override fun setView(view: View?): AlertDialog.Builder {
    if (unitId == null) throw KotlinNullPointerException("NativeId must not null")
    return super.setView(view)
  }

  override fun show(): AlertDialog {
    if (unitId == null) throw KotlinNullPointerException("NativeId must not null")
    return super.show()
  }
}
