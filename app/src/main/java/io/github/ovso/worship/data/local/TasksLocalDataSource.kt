package io.github.ovso.worship.data.local

import android.content.Context
import com.google.gson.Gson
import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.extensions.fromJson
import io.github.ovso.worship.utils.AssetsUtil
import io.reactivex.rxjava3.core.Single

class TasksLocalDataSource(private val context: Context) {

  fun churches(): Single<List<ChurchEntity>> {
    return Single.fromCallable {
      AssetsUtil.getJsonDataFromAsset(context, "churches.json")?.let {
        Gson().fromJson<List<ChurchEntity>>(it)
      } ?: listOf()
    }
  }
}
