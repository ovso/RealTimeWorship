package io.github.ovso.worship.data.mapper

import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.view.PlayerModel
import io.github.ovso.worship.data.view.VideoModel

interface Mapper<in T, out K> {
  abstract fun toModels(response: T): K
}

fun VideoModel.toPlayerModel(): PlayerModel {
  return PlayerModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.videoId
  )
}

fun PlayerModel.toBookmarkEntity(): BookmarkEntity {
  return BookmarkEntity(
    title = this.title,
    thumbnail = this.thumbnail,
    video_id = this.videoId
  )
}
