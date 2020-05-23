package io.github.ovso.worship.data.local

import android.content.Context
import com.google.gson.Gson
import io.github.ovso.worship.data.local.model.Church
import io.github.ovso.worship.extensions.fromJson
import io.github.ovso.worship.utils.AssetsUtil
import io.reactivex.rxjava3.core.Single

class TasksLocalDataSource(private val context: Context) {

  fun churches(): Single<List<Church>> {
    return Single.fromCallable {
      AssetsUtil.getJsonDataFromAsset(context, "churches.json")?.let {
        Gson().fromJson<List<Church>>(it)
      } ?: listOf()
    }
  }
}
