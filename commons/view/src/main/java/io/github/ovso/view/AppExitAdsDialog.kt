package io.github.ovso.view

import android.app.Dialog
import android.content.Context
import android.view.View
import io.github.ovso.view.databinding.DialogNativeAdsBinding

class AppExitAdsDialog(context: Context): Dialog(context) {

  private val binding = DialogNativeAdsBinding.inflate(layoutInflater)

  override fun setContentView(view: View) {
    super.setContentView(binding.root)
  }
}
