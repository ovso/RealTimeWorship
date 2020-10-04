@file:Suppress("NonAsciiCharacters")

package io.github.ovso.worship

import io.github.ovso.worship.data.remote.TasksRemoteDataSource
import io.github.ovso.worship.data.toVideoModels
import io.github.ovso.worship.data.view.VideoModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryTest {
  private val tasksRemoteDataSource by lazy { TasksRemoteDataSource() }
  private val channelId = "UCQ6HHfdElel_hK8XzREU-2g"

  @Test
  fun `레파지토리 테스트`() {

    fun onFailure(t: Throwable) {
      println(t.message)
      t.printStackTrace()
    }

    fun onSuccess(items: List<VideoModel>) {
      println("items size = ${items.count()}")
    }

    tasksRemoteDataSource.videos(TasksRemoteDataSource.CategoryId.ChannelId(channelId))
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
