package io.github.ovso.worship.data

import com.google.gson.Gson
import com.google.gson.JsonElement
import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.data.local.model.HistoryEntity
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.github.ovso.worship.data.view.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable

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

fun PlayerModel.toHistoryEntity(): HistoryEntity {
  return HistoryEntity(
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

fun HistoryEntity.toHistoryModel(): HistoryModel {
  return HistoryModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.video_id
  )
}

fun List<HistoryEntity>.toHistoryModels(): Single<List<HistoryModel>> {
  return toObservable().map {
    it.toHistoryModel()
  }.toList()
}

fun HistoryModel.toPlayerModel(): PlayerModel {
  return PlayerModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.videoId
  )
}

fun HistoryModel.toEntity(): HistoryEntity {
  return HistoryEntity(
    title = this.title,
    thumbnail = this.thumbnail,
    video_id = this.videoId
  )
}

fun List<ChurchEntity>.toChurchModels(): List<HomeItemModel> {
  return this.toObservable().map {
    HomeItemModel(
      title = it.title,
      channelId = it.channelId
    )
  }.toList().blockingGet()
}

fun List<VideoResponse>.toVideoModels(): List<VideoModel> {
  return this.toObservable().map {
    @Suppress("USELESS_ELVIS")
    VideoModel(
      title = it.gridVideoRenderer.title.runs.first().text ?: "",
      thumbnail = it.gridVideoRenderer.thumbnail.thumbnails.last().url,
      videoId = it.gridVideoRenderer.videoId
    )
  }.toList().blockingGet()
}

fun JsonElement.toVideoResponses(): List<VideoResponse> {
  try {
    val gson = Gson()
    val asJsonArray = asJsonObject["contents"]
      .asJsonObject["twoColumnBrowseResultsRenderer"]
      .asJsonObject["tabs"]
      .asJsonArray[1]
      .asJsonObject["tabRenderer"]
      .asJsonObject["content"]
      .asJsonObject["sectionListRenderer"]
      .asJsonObject["contents"]
      .asJsonArray.first()
      .asJsonObject["itemSectionRenderer"]
      .asJsonObject["contents"]
      .asJsonArray.first()
      .asJsonObject["gridRenderer"]
      .asJsonObject["items"].asJsonArray
    return Observable.fromIterable(asJsonArray).map {
      gson.fromJson(it.toString(), VideoResponse::class.java)
    }.toList().blockingGet()
  } catch (e: Throwable) {
    return listOf()
  }
}
