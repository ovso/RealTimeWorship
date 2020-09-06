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
  private val channelId = "UCwibCFAl4jqx5T5BueGc9ig"

  @Test
  fun `레파지토리 테스트`() {

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    fun onSuccess(items: List<VideoModel>) {
      println("items size = ${items.count()}")
    }

    tasksRemoteDataSource.videos2(channelId)
      .map { it.toVideoModels() }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }

  object SchedulerProvider {
    fun io(): Scheduler = Schedulers.trampoline()
    fun ui(): Scheduler = Schedulers.trampoline()
  }
}
