@file:Suppress("NonAsciiCharacters")

package io.github.ovso.worship

import com.google.gson.annotations.SerializedName
import io.github.ovso.worship.data.remote.TasksRemoteDataSource
import io.github.ovso.worship.data.remote.response.Thumbnail
import io.github.ovso.worship.data.remote.response.Title
import io.github.ovso.worship.data.toVideoModels
import io.github.ovso.worship.data.view.VideoModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.Jsoup
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryTest {
  private val tasksRemoteDataSource by lazy { TasksRemoteDataSource() }
  private val channelId = "UC6vNHBFM5VLNF53CKycyNZw"

  @Test
  fun `레파지토리 테스트`() {

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    fun onSuccess(items: List<VideoModel>) {
      println("items size = ${items.count()}")
    }

    tasksRemoteDataSource.videos(channelId)
      .map { it.toVideoModels() }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }

  data class Video(
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("title")
    val title: Title,
    @SerializedName("videoId")
    val videoId: String,
    @SerializedName("channelId")
    val channelId: String
  )

  @Test
  fun `주일설교`() {

    /*
          val document = Jsoup.connect("https://www.youtube.com/channel/$channelId/videos").get()
      val scriptElements = document.getElementsByTag("script")
      val prefix = "[{\"gridVideoRenderer"
      val itemsElement = scriptElements.first { it.data().contains(prefix) }
      val startIndex = itemsElement.data().indexOf(prefix)
      val endIndex = itemsElement.data().indexOf(",\"continuations\"")
      val jsonArrayString = itemsElement.data().substring(startIndex, endIndex)
      Gson().fromJson<List<VideoResponse>>(jsonArrayString)

     */
    val url = "https://www.youtube.com/playlist?list=PLGrnuYtP8VGGhqMgCo9FNwCB-_iwfcFCM"
    val document = Jsoup.connect(url).get()
    val scriptElements = document.getElementsByTag("script")
    val prefix = "[{\"playlistVideoRenderer"
    val itemsElement = scriptElements.first { it.data().contains(prefix) }
    val startIndex = itemsElement.data().indexOf(prefix)
    val endIndex = itemsElement.data().indexOf(",\"continuations\"")
    val jsonArrayString = itemsElement.data().substring(startIndex, endIndex)
    println(document)
  }

  @Test
  fun `주일설교2`() {

    /*
          val document = Jsoup.connect("https://www.youtube.com/channel/$channelId/videos").get()
      val scriptElements = document.getElementsByTag("script")
      val prefix = "[{\"gridVideoRenderer"
      val itemsElement = scriptElements.first { it.data().contains(prefix) }
      val startIndex = itemsElement.data().indexOf(prefix)
      val endIndex = itemsElement.data().indexOf(",\"continuations\"")
      val jsonArrayString = itemsElement.data().substring(startIndex, endIndex)
      Gson().fromJson<List<VideoResponse>>(jsonArrayString)

     */
    val url = "https://www.youtube.com/playlist?list=PLGrnuYtP8VGGhqMgCo9FNwCB-_iwfcFCM"
    val document = Jsoup.connect(url).get()
    val elementsByTag = document.getElementsByTag("ytd-app")
    val masthead = elementsByTag.first()

    val elementsByTag2 = masthead.getElementsByTag("ytd-two-column-browse-results-renderer")
    val first = elementsByTag2.first()
    val scriptElements = document.getElementsByTag("script")
    val prefix = "[{\"playlistVideoRenderer"
    val suffix = "endpoint"
    val itemsElement = scriptElements.first { it.data().contains(prefix) }
    val startIndex = itemsElement.data().indexOf(prefix)
    val endIndex = itemsElement.data().indexOf("}}]}}]}}}}")
    val jsonArrayString = itemsElement.data().substring(startIndex, endIndex)
    println(document)
  }

  object SchedulerProvider {
    fun io(): Scheduler = Schedulers.trampoline()
    fun ui(): Scheduler = Schedulers.trampoline()
  }
}
