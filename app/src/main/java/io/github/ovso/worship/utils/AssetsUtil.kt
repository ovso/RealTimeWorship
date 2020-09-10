package io.github.ovso.worship.utils

import android.content.Context
import java.io.IOException

object AssetsUtil {
  fun getJsonDataFromAsset(context: Context, fileName: String): String? = try {
    context.assets.open(fileName).bufferedReader().use { it.readText() }
  } catch (e: IOException) {
    e.printStackTrace()
    null
  }
}
