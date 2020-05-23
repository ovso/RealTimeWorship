package io.github.ovso.worship.data

import io.github.ovso.worship.data.local.TasksLocalDataSource
import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.data.remote.TasksRemoteDataSource
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.reactivex.rxjava3.core.Single

class TasksRepository(
  private val remoteDataSource: TasksRemoteDataSource,
  private val localDataSource: TasksLocalDataSource
) : TasksDataSource {

  override fun videos(channelId: String): Single<List<VideoResponse>> {
    return remoteDataSource.videos(channelId)
  }

  override fun churches(): Single<List<ChurchEntity>> {
    return localDataSource.churches()
  }

}
