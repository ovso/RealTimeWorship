package io.github.ovso.worship

import com.google.gson.Gson
import io.github.ovso.worship.data.network.ServiceLocator
import io.github.ovso.worship.data.network.model.VideoResponse
import io.github.ovso.worship.extensions.fromJson
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Test
import java.lang.StringBuilder

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryTest {
    private val tasksRepository by lazy { ServiceLocator.provideTasksRepository() }

    @Test
    fun `레파지토리 테스트`() {

        fun onFailure(t: Throwable) {
            println(t.message)
        }

        fun onSuccess(items: List<VideoResponse>) {
            println("items size = ${items.count()}")
        }

        val channelId = "UC6vNHBFM5VLNF53CKycyNZw"
        tasksRepository.getVideos(channelId)
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.ui())
            .subscribe(::onSuccess, ::onFailure)
    }

    object SchedulerProvider {
        fun io(): Scheduler = Schedulers.trampoline()
        fun ui(): Scheduler = Schedulers.trampoline()
    }
}
