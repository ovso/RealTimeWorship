package io.github.ovso.worship.extensions

import android.content.Context

fun Context.getIndicatorSize(): Int {
  val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
  return when (resourceId > 0) {
    true -> resources.getDimensionPixelSize(resourceId)
    false -> 0
  }
}
