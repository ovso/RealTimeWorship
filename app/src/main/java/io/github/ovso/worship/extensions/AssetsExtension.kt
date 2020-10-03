package io.github.ovso.worship.extensions

import android.content.Context
import java.io.IOException

fun Context.getStringFromAssets(fileName: String): String? = try {
  assets.open(fileName).bufferedReader().use { it.readText() }
} catch (e: IOException) {
  e.printStackTrace()
  null
}
