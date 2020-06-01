package io.github.ovso.worship.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Point

fun Context.getIndicatorSize(): Int {
  val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
  return when (resourceId > 0) {
    true -> resources.getDimensionPixelSize(resourceId)
    false -> 0
  }
}

fun Activity.getScreenSize(): Point {
  val display = windowManager.defaultDisplay
  val size = Point()
  display.getSize(size)
  return size
}
