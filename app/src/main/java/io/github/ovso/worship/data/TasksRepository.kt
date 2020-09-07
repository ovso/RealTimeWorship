package io.github.ovso.worship.data

import androidx.lifecycle.LiveData
import io.github.ovso.worship.data.local.TasksLocalDataSource
import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.data.local.model.HistoryEntity
import io.github.ovso.worship.data.remote.TasksRemoteDataSource
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Single

class TasksRepository(
  private val remoteDataSource: TasksRemoteDataSource,
  private val localDataSource: TasksLocalDataSource
) : TasksDataSource {

  override fun videos(channelId: String): Single<List<VideoResponse>> {
    return remoteDataSource.videos2(channelId).subscribeOn(SchedulerProvider.io())
  }

  override fun churches(): Single<List<ChurchEntity>> {
    return localDataSource.churches()
  }

  override fun addBookmark(entity: BookmarkEntity) {
    localDataSource.addBookmark(entity)
  }

  override fun delBookmark(entity: BookmarkEntity): Int {
    return localDataSource.delBookmark(entity)
  }

  override fun getBookmark(videoId: String): LiveData<BookmarkEntity?> {
    return localDataSource.getBookmark(videoId)
  }

  override fun getBookmarks(): LiveData<List<BookmarkEntity>> {
    return localDataSource.getBookmarks()
  }

  override fun getHistories(): LiveData<List<HistoryEntity>> {
    return localDataSource.getHistories()
  }

  override fun getHistory(videoId: String): LiveData<HistoryEntity?> {
    return localDataSource.getHistory(videoId)
  }

  override fun addHistory(entity: HistoryEntity) {
    localDataSource.addHistory(entity)
  }

  override fun delHistory(entity: HistoryEntity): Int {
    return localDataSource.delHistory(entity)
  }

}
