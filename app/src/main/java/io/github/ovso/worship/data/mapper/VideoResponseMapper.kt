package io.github.ovso.worship.data.mapper

import io.github.ovso.worship.data.network.response.VideoResponse
import io.github.ovso.worship.data.view.VideoModel

object VideoResponseMapper : ResponseMapper<VideoResponse, VideoModel> {
    override fun fromResponse(response: VideoResponse): VideoModel {
        return VideoModel(
            title = response.gridVideoRenderer.title.simpleText,
            thumbnail = response.gridVideoRenderer.thumbnail.thumbnails.last().url,
            videoId = response.gridVideoRenderer.videoId
        )
    }
}
