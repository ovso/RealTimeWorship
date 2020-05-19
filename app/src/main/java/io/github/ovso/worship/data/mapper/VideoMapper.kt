package io.github.ovso.worship.data.mapper

import io.github.ovso.worship.data.remote.response.VideoResponse
import io.github.ovso.worship.data.view.VideoModel
import io.reactivex.rxjava3.core.Observable

object VideoMapper : Mapper<VideoResponse, VideoModel> {
    override fun toModels(response: VideoResponse): VideoModel {
        return VideoModel(
            title = response.gridVideoRenderer.title.simpleText,
            thumbnail = response.gridVideoRenderer.thumbnail.thumbnails.last().url,
            videoId = response.gridVideoRenderer.videoId
        )
    }
}

object VideoModelMapper {
    fun fromResponses(responses: List<VideoResponse>): List<VideoModel> {
        return Observable.fromIterable(responses).map {
            VideoModel(
                title = it.gridVideoRenderer.title.simpleText,
                thumbnail = it.gridVideoRenderer.thumbnail.thumbnails.last().url,
                videoId = it.gridVideoRenderer.videoId
            )
        }.toList().blockingGet()
    }
}
