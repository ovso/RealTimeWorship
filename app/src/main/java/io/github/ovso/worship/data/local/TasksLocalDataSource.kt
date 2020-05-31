package io.github.ovso.worship.data.local

import android.content.Context
import com.google.gson.Gson
import io.github.ovso.worship.app.App
import io.github.ovso.worship.data.local.model.BookmarkEntity
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

  fun bookmark(title: String, thumbnail: String, video_id: String) {
    val database = (context.applicationContext as? App)?.database
    database?.bookmarkDao()?.insert(
      BookmarkEntity(
        title = title,
        thumbnail = thumbnail,
        video_id = video_id
      )
    )
  }
}
