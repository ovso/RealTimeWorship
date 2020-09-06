@file:Suppress("NonAsciiCharacters")

package io.github.ovso.worship

import com.google.gson.Gson
import com.google.gson.JsonElement
import io.github.ovso.worship.data.remote.response.VideoResponse
import io.github.ovso.worship.extensions.fromJson
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.Jsoup
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ParsingTest {
  private val channelId = "UCwibCFAl4jqx5T5BueGc9ig"

  @Test
  fun `청파교회`() {
    val document =
      Jsoup.connect("https://www.youtube.com/channel/$channelId/videos").timeout(60 * 1000).get()
    val scriptElements = document.getElementsByTag("script")
    val prefix = "{\"responseContext\":{"
    val itemsElement = scriptElements.first { it.data().contains(prefix) }
    val startIndex = itemsElement.data().indexOf(prefix)
//      val endIndex = itemsElement.data().lastIndexOf("ytInitialPlayerResponse")
    val endIndex = itemsElement.data().lastIndexOf("window[\"ytInitialPlayerResponse")
    val jsonString = itemsElement.data().substring(startIndex, endIndex-1)
//    Gson().fromJson<List<VideoResponse>>(jsonArrayString)
    val fromJson = Gson().fromJson(jsonString, JsonElement::class.java)
  }

  object SchedulerProvider {
    fun io(): Scheduler = Schedulers.trampoline()
    fun ui(): Scheduler = Schedulers.trampoline()
  }
}
