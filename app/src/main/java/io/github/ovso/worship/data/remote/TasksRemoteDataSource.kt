package io.github.ovso.worship.data.remote

import com.google.gson.Gson
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.github.ovso.worship.extensions.fromJson
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup

class TasksRemoteDataSource {

  fun videos(channelId: String): Single<List<VideoResponse>> {
    return Single.fromCallable {
      val document =
        Jsoup.connect("https://www.youtube.com/channel/$channelId/videos").timeout(60 * 1000).get()
      val scriptElements = document.getElementsByTag("script")
      val prefix = "[{\"gridVideoRenderer"
      val itemsElement = scriptElements.first { it.data().contains(prefix) }
      val startIndex = itemsElement.data().indexOf(prefix)
      val endIndex = itemsElement.data().indexOf(",\"continuations\"")
      val jsonArrayString = itemsElement.data().substring(startIndex, endIndex)
      Gson().fromJson<List<VideoResponse>>(jsonArrayString)
    }
  }

  fun videos2(channelId: String): Single<List<VideoResponse>> {
    return Single.fromCallable {
      val document =
        Jsoup.connect("https://www.youtube.com/channel/$channelId/videos").timeout(60 * 1000).get()
      val scriptElements = document.getElementsByTag("script")
      val prefix = "{\"responseContext\":{"
      val itemsElement = scriptElements.first { it.data().contains(prefix) }
      val startIndex = itemsElement.data().indexOf(prefix)
//      val endIndex = itemsElement.data().lastIndexOf("ytInitialPlayerResponse")
      val endIndex = itemsElement.data().lastIndexOf("window[\"ytInitialPlayerResponse")
      val jsonArrayString = itemsElement.data().substring(startIndex, endIndex)
      Gson().fromJson<List<VideoResponse>>(jsonArrayString)
    }
  }
}
