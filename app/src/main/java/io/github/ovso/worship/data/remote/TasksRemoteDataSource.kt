package io.github.ovso.worship.data.remote

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.github.ovso.worship.data.toVideoResponses
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup

class TasksRemoteDataSource {

  fun videos(channelId: String): Single<List<VideoResponse>> {
    return Single.fromCallable {
      val document =
        Jsoup.connect("https://www.youtube.com/channel/$channelId/videos").timeout(60 * 1000).get()
      val scriptElements = document.getElementsByTag("script")
      val prefix = "{\"responseContext\":{"
      val itemsElement = scriptElements.first { it.data().contains(prefix) }
      val startIndex = itemsElement.data().indexOf(prefix)
      val endIndex = itemsElement.data().lastIndexOf("window[\"ytInitialPlayerResponse")
      val fullJsonString = itemsElement.data().substring(startIndex, endIndex)
      val stableJsonString = fullJsonString.substring(0, fullJsonString.indexOf(";"))
      val json = GsonBuilder().setLenient().create().fromJson(
        stableJsonString, JsonElement::class.java
      )
      json.toVideoResponses()
    }
  }
}
