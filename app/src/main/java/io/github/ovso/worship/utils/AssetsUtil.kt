package io.github.ovso.worship.utils

import android.content.Context
import kotlinx.io.errors.IOException

object AssetsUtil {
  fun getJsonDataFromAsset(context: Context, fileName: String): String? = try {
    context.assets.open(fileName).bufferedReader().use { it.readText() }
  } catch (ioException: IOException) {
    ioException.printStackTrace()
    null
  }
}
