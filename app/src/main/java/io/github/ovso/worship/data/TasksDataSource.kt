package io.github.ovso.worship.data

import io.github.ovso.worship.data.network.model.VideoResponse
import io.reactivex.rxjava3.core.Single

interface TasksDataSource {
    fun getVideos(channelId: String): Single<List<VideoResponse>>
}