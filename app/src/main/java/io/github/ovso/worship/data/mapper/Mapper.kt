package io.github.ovso.worship.data.mapper

import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.view.BookmarkModel
import io.github.ovso.worship.data.view.PlayerModel
import io.github.ovso.worship.data.view.VideoModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable

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

fun BookmarkModel.toPlayerModel(): PlayerModel {
  return PlayerModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.videoId
  )
}

fun BookmarkModel.toEntity(): BookmarkEntity {
  return BookmarkEntity(
    title = this.title,
    thumbnail = this.thumbnail,
    video_id = this.videoId
  )
}

fun PlayerModel.toBookmarkEntity(): BookmarkEntity {
  return BookmarkEntity(
    title = this.title,
    thumbnail = this.thumbnail,
    video_id = this.videoId
  )
}

fun BookmarkEntity.toBookmarkModel(): BookmarkModel {
  return BookmarkModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.video_id
  )
}

fun List<BookmarkEntity>.toBookmarkModels(): Single<List<BookmarkModel>> {
  return toObservable().map {
    it.toBookmarkModel()
  }.toList()
}
