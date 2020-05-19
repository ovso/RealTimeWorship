package io.github.ovso.worship.data.mapper

import io.github.ovso.worship.data.remote.response.VideoResponse
import io.github.ovso.worship.data.view.VideoModel
import io.reactivex.rxjava3.kotlin.toObservable

object VideoModelMapper {
    fun fromResponses(responses: List<VideoResponse>): List<VideoModel> {
        return responses.toObservable().map {
            VideoModel(
                title = it.gridVideoRenderer.title.simpleText,
                thumbnail = it.gridVideoRenderer.thumbnail.thumbnails.last().url,
                videoId = it.gridVideoRenderer.videoId
            )
        }.toList().blockingGet()
    }
}
