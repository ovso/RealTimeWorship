package io.github.ovso.worship.data

import androidx.lifecycle.LiveData
import io.github.ovso.worship.data.local.TasksLocalDataSource
import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.data.local.model.HistoryEntity
import io.github.ovso.worship.data.local.model.StoryEntity
import io.github.ovso.worship.data.remote.TasksRemoteDataSource
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TasksRepository @Inject constructor(
  private val remoteDataSource: TasksRemoteDataSource,
  private val localDataSource: TasksLocalDataSource
) : Repository {

  override fun videos(videoCategory: TasksRemoteDataSource.CategoryId.ChannelId): Single<List<VideoResponse>> {
    return remoteDataSource.videos(videoCategory)
  }

  override fun videos(videoCategory: TasksRemoteDataSource.CategoryId.PlayListId): Single<List<VideoResponse>> {
    return remoteDataSource.videos(videoCategory)
  }

  override fun churches(): Single<List<ChurchEntity>> {
    return localDataSource.churches()
  }

  override suspend fun stories(): List<StoryEntity> {
    return localDataSource.stories()
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

  override suspend fun getBookmarksAsync(): List<BookmarkEntity> {
    return localDataSource.getBookmarksAsync()
  }

  override fun getHistories(): LiveData<List<HistoryEntity>> {
    return localDataSource.getHistories()
  }

  override suspend fun getHistoriesAsync(): List<HistoryEntity> {
    return localDataSource.getHistoriesAsync()
  }

  override fun getHistory(videoId: String): LiveData<HistoryEntity?> {
    return localDataSource.getHistory(videoId)
  }

  override suspend fun addHistory(entity: HistoryEntity) {
    localDataSource.addHistory(entity)
  }

  override fun delHistory(entity: HistoryEntity): Int {
    return localDataSource.delHistory(entity)
  }

}
