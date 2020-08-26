package io.github.ovso.worship.extensions

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import io.github.ovso.worship.R

fun Fragment.showBottomNav() {
  activity?.findViewById<View>(R.id.bnv_main)?.isVisible = true
}

fun Fragment.hideBottomNav() {
  activity?.findViewById<View>(R.id.bnv_main)?.isVisible = false
}
