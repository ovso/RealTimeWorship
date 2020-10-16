package io.github.ovso.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import io.github.ovso.view.databinding.DialogNativeAdsBinding

class NativeAdsDialog2(context: Context) : AlertDialog.Builder(context) {

  private val binding by lazy {
    DialogNativeAdsBinding.inflate(LayoutInflater.from(context))
  }

  private var unitId: String? = null
  fun setUnitId(unitId: String?): AlertDialog.Builder {
    this.unitId = unitId
    val templateView = binding.root
    val builder = AdLoader.Builder(context, unitId).apply {
      forUnifiedNativeAd {
        templateView.setNativeAd(it)
      }
    }
    val adLoader = builder.build()
    val adRequest = AdRequest.Builder().build()
    adLoader.loadAd(adRequest)
    return setView(binding.root)
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
