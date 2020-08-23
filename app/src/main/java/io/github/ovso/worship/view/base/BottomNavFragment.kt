package io.github.ovso.worship.view.base

import android.view.View
import androidx.core.view.isVisible
import io.github.ovso.worship.R

abstract class BottomNavFragment : ActionBarFragment() {
  open fun showBottomNav() {
    activity?.findViewById<View>(R.id.bnv_main)?.isVisible = true
  }

  open fun hideBottomNav() {
    activity?.findViewById<View>(R.id.bnv_main)?.isVisible = false
  }
}
