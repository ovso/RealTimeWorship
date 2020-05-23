@file:Suppress("NonAsciiCharacters")

package io.github.ovso.worship

import io.github.ovso.worship.data.mapper.VideoModelMapper
import io.github.ovso.worship.data.remote.ServiceLocator
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
  private val tasksRepository by lazy { ServiceLocator.provideTasksRepository() }
  private val channelId = "UC6vNHBFM5VLNF53CKycyNZw"

  @Test
  fun `레파지토리 테스트`() {

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    fun onSuccess(items: List<VideoModel>) {
      println("items size = ${items.count()}")
    }

    tasksRepository.videos(channelId)
      .map(VideoModelMapper::fromResponses)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }

  @Test
  fun `Jsoup 테스트`() {
    val className = "video-skeleton"
    val document =
      Jsoup.connect("https://www.youtube.com/channel/UC6vNHBFM5VLNF53CKycyNZw/videos?view=0&sort=dd&shelf_id=0")
        .get()
    val elementsByClass = document.body().getElementsByClass(className)
    val contents = document.body().getElementById("contents")
    println("class name = $contents")
  }

  object SchedulerProvider {
    fun io(): Scheduler = Schedulers.trampoline()
    fun ui(): Scheduler = Schedulers.trampoline()
  }
}
