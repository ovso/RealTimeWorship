@file:Suppress("NonAsciiCharacters")

package io.github.ovso.worship

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import io.github.ovso.worship.data.toVideoResponses
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.Jsoup
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class JsoupTest {
  @Test
  fun parsing() {
    val document =
      Jsoup.connect("https://www.youtube.com/playlist?list=PLC2TbiZou_9wyEJLiRixWsS3TKuygLpCF")
        .timeout(60 * 1000)
        .ignoreHttpErrors(true).get()
    val scriptElements = document.getElementsByTag("script")
    val prefix = "{\"responseContext\":{"
    val itemsElement = scriptElements.first { it.data().contains(prefix) }
    val startIndex = itemsElement.data().indexOf(prefix)
    val endIndex = itemsElement.data().lastIndexOf("window[\"ytInitialPlayerResponse")
    val fullJsonString = itemsElement.data().substring(startIndex, endIndex)
    val indexOfLastSemicolon = fullJsonString.indexOfLast {
      it == ";".single()
    }
    val stableJsonString = fullJsonString.substring(0, indexOfLastSemicolon)
    val json = GsonBuilder().setLenient().create().fromJson(
      stableJsonString, JsonElement::class.java
    )
    json.toVideoResponses()
  }

  object SchedulerProvider {
    fun io(): Scheduler = Schedulers.trampoline()
    fun ui(): Scheduler = Schedulers.trampoline()
  }
}
