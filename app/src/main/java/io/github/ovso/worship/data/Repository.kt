package io.github.ovso.worship.data

import androidx.lifecycle.LiveData
import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.data.local.model.HistoryEntity
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.reactivex.rxjava3.core.Single

interface Repository {
  fun videos(channelId: String): Single<List<VideoResponse>>
  fun churches(): Single<List<ChurchEntity>>
  fun addBookmark(entity: BookmarkEntity)
  fun delBookmark(entity: BookmarkEntity): Int
  fun getBookmark(videoId: String): LiveData<BookmarkEntity?>
  fun getBookmarks(): LiveData<List<BookmarkEntity>>
  suspend fun getBookmarksAsync(): List<BookmarkEntity>
  suspend fun getHistoriesAsync(): List<HistoryEntity>
  fun getHistories(): LiveData<List<HistoryEntity>>
  fun getHistory(videoId: String): LiveData<HistoryEntity?>
  fun addHistory(entity: HistoryEntity)
  fun delHistory(entity: HistoryEntity): Int
}