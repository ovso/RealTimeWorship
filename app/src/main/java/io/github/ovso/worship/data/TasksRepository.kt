package io.github.ovso.worship.data

import io.github.ovso.worship.data.remote.TasksRemoteDataSource
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.reactivex.rxjava3.core.Single

class TasksRepository(private val remoteDataSource: TasksRemoteDataSource) : TasksDataSource {

    override fun getVideos(channelId: String): Single<List<VideoResponse>> {
        return remoteDataSource.getVideos(channelId)
    }
}
