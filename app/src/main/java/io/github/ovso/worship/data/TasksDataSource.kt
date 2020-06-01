package io.github.ovso.worship.data

import androidx.lifecycle.LiveData
import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.reactivex.rxjava3.core.Single

interface TasksDataSource {
  fun videos(channelId: String): Single<List<VideoResponse>>
  fun churches(): Single<List<ChurchEntity>>
  fun addBookmark(entity: BookmarkEntity)
  fun delBookmark(entity: BookmarkEntity): Int
  fun getBookmark(videoId: String): LiveData<BookmarkEntity?>
}
