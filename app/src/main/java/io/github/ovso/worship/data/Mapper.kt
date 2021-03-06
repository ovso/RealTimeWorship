package io.github.ovso.worship.data

import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.github.ovso.worship.data.args.VideoArgs
import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.data.local.model.HistoryEntity
import io.github.ovso.worship.data.local.model.StoryEntity
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.github.ovso.worship.data.view.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable

@WorkerThread
fun VideoModel.toPlayerModel(): PlayerModel {
  return PlayerModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.videoId
  )
}

@WorkerThread
fun BookmarkModel.toPlayerModel(): PlayerModel {
  return PlayerModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.videoId
  )
}

@WorkerThread
fun BookmarkModel.toEntity(): BookmarkEntity {
  return BookmarkEntity(
    title = this.title,
    thumbnail = this.thumbnail,
    video_id = this.videoId
  )
}

@WorkerThread
fun PlayerModel.toBookmarkEntity(): BookmarkEntity {
  return BookmarkEntity(
    title = this.title,
    thumbnail = this.thumbnail,
    video_id = this.videoId
  )
}

@WorkerThread
fun PlayerModel.toHistoryEntity(): HistoryEntity {
  return HistoryEntity(
    title = this.title,
    thumbnail = this.thumbnail,
    video_id = this.videoId
  )
}

@WorkerThread
fun BookmarkEntity.toBookmarkModel(): BookmarkModel {
  return BookmarkModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.video_id
  )
}

@WorkerThread
fun List<BookmarkEntity>.toBookmarkModels(): Single<List<BookmarkModel>> {
  return toObservable().map {
    it.toBookmarkModel()
  }.toList()
}

@WorkerThread
fun HistoryEntity.toHistoryModel(): HistoryModel {
  return HistoryModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.video_id
  )
}

@WorkerThread
fun List<HistoryEntity>.toHistoryModels(): List<HistoryModel> {
  return toObservable().map {
    it.toHistoryModel()
  }.toList().blockingGet()
}

@WorkerThread
fun HistoryModel.toPlayerModel(): PlayerModel {
  return PlayerModel(
    title = this.title,
    thumbnail = this.thumbnail,
    videoId = this.videoId
  )
}

@WorkerThread
fun HistoryModel.toEntity(): HistoryEntity {
  return HistoryEntity(
    title = this.title,
    thumbnail = this.thumbnail,
    video_id = this.videoId
  )
}

@WorkerThread
fun List<ChurchEntity>.toChurchModels(): List<HomeItemModel> {
  return this.toObservable().map {
    HomeItemModel(
      title = it.title,
      channelId = it.channelId
    )
  }.toList().blockingGet()
}

@WorkerThread
fun List<VideoResponse>.toVideoModels(): List<VideoModel> {
  return toObservable().map {
    if (it.gridVideoRenderer != null) {
      @Suppress("USELESS_ELVIS")
      VideoModel(
        title = it.gridVideoRenderer.title.runs.first().text ?: "",
        thumbnail = it.gridVideoRenderer.thumbnail.thumbnails.last().url,
        videoId = it.gridVideoRenderer.videoId
      )
    } else {
      @Suppress("USELESS_ELVIS")
      VideoModel(
        title = it.playlistVideoRenderer.title.runs.first().text ?: "",
        thumbnail = it.playlistVideoRenderer.thumbnail.thumbnails.last().url,
        videoId = it.playlistVideoRenderer.videoId
      )

    }
  }.toList().blockingGet()
}

@WorkerThread
fun JsonElement.channelJsonToVideoResponses(): List<VideoResponse> {
  try {
    val gson = Gson()
    val videoItemsJson = asJsonObject["contents"]
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
    return Observable.fromIterable(videoItemsJson).map {
      gson.fromJson(it.toString(), VideoResponse::class.java)
    }.toList().blockingGet()
  } catch (e: Throwable) {
    return listOf()
  }
}

@WorkerThread
fun JsonElement.playlistJsonToVideoResponses(): List<VideoResponse> {
  try {
    val gson = Gson()
    val videoItemsJson = asJsonObject["contents"]
      .asJsonObject["twoColumnBrowseResultsRenderer"]
      .asJsonObject["tabs"]
      .asJsonArray[0]
      .asJsonObject["tabRenderer"]
      .asJsonObject["content"]
      .asJsonObject["sectionListRenderer"]
      .asJsonObject["contents"]
      .asJsonArray.first()
      .asJsonObject["itemSectionRenderer"]
      .asJsonObject["contents"]
      .asJsonArray.first()
      .asJsonObject["playlistVideoListRenderer"]
      .asJsonObject["contents"]
      .asJsonArray
    return Observable.fromIterable(videoItemsJson).map {
      gson.fromJson(it.toString(), VideoResponse::class.java)
    }.toList().blockingGet()
  } catch (e: Throwable) {
    println(e)
    return listOf()
  }
}

@WorkerThread
fun List<StoryEntity>.toStoryModels(): List<StoryItemModel> {
  return map {
    StoryItemModel(
      id = it.id,
      title = it.title,
      category = it.category
    )
  }.sortedBy {
    it.title
  }
}

@WorkerThread
fun StoryItemModel.toVideoArgs(): VideoArgs {
  return VideoArgs(
    id = id,
    title = title,
    category = category
  )
}

@WorkerThread
fun HomeItemModel.toVideoArgs(): VideoArgs {
  return VideoArgs(
    id = channelId,
    title = title,
    category = "channel"
  )
}
