package io.github.ovso.worship.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import io.github.ovso.worship.app.App
import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.data.local.model.HistoryEntity
import io.github.ovso.worship.data.local.model.StoryEntity
import io.github.ovso.worship.extensions.fromJson
import io.github.ovso.worship.extensions.getStringFromAssets
import io.github.ovso.worship.utils.AssetsUtil
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TasksLocalDataSource @Inject constructor(private val context: Context) {
  private val database = (context.applicationContext as App).database

  fun churches(): Single<List<ChurchEntity>> {
    return Single.fromCallable {
      AssetsUtil.getJsonDataFromAsset(context, "churches.json")?.let {
        Gson().fromJson<List<ChurchEntity>>(it)
      } ?: listOf()
    }
  }

  suspend fun stories(): List<StoryEntity> {
    return context.getStringFromAssets("story.json")?.let {
      Gson().fromJson<List<StoryEntity>>(it)
    } ?: listOf()
  }

  fun addBookmark(entity: BookmarkEntity) {
    database.bookmarkDao().insert(entity)
  }

  fun delBookmark(entity: BookmarkEntity): Int {
    return database.bookmarkDao().delete(entity)
  }

  fun getBookmark(videoId: String): LiveData<BookmarkEntity?> {
    return database.bookmarkDao().getBookmark(videoId)
  }

  fun getBookmarks(): LiveData<List<BookmarkEntity>> {
    return database.bookmarkDao().bookmarks()
  }

  suspend fun getBookmarksAsync(): List<BookmarkEntity> {
    return database.bookmarkDao().bookmarksAsync()
  }

  fun getHistories(): LiveData<List<HistoryEntity>> {
    return database.historyDao().histories()
  }

  suspend fun getHistoriesAsync(): List<HistoryEntity> {
    return database.historyDao().historiesAsync()
  }

  fun getHistory(videoId: String): LiveData<HistoryEntity?> {
    return database.historyDao().getHistory(videoId)
  }

  suspend fun addHistory(entity: HistoryEntity) {
    return database.historyDao().addHistory(entity)
  }

  fun delHistory(entity: HistoryEntity): Int {
    return database.historyDao().delete(entity)
  }
}
