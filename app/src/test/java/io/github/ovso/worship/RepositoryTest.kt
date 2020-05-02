package io.github.ovso.worship

import com.google.gson.Gson
import io.github.ovso.worship.data.network.ServiceLocator
import io.github.ovso.worship.data.network.model.VideoResponse
import io.github.ovso.worship.extensions.fromJson
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Test

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

        fun onSuccess(items: Document) {
            println("items size = ${items.toString().length}")
        }

//        tasksRepository.getVideos("UC6vNHBFM5VLNF53CKycyNZw")
//            .subscribe(::onSuccess, ::onFailure)

    }

    @Test
    fun `Jsoup을 통한 데이터 가공`() {
        val get =
            Jsoup.connect("https://www.youtube.com/channel/UC6vNHBFM5VLNF53CKycyNZw/videos")
                .get()
        val elementsByTag = get.getElementsByTag("script")
        val prefix = "[{\"gridVideoRenderer"
        elementsByTag.forEach {
            if (it.data().contains(prefix)) {
                val startIndex = it.data().indexOf(prefix)
                val endIndex = it.data().indexOf(",\"continuations\"")
                val subSequence = it.data().substring(startIndex, endIndex)
                val fromJson1 = Gson().fromJson<List<VideoResponse>>(subSequence)
                println("size = ${fromJson1.size}")
            }
        }
    }

    @Test
    fun `데이터 가공시 로직 줄이기`() {
        val get =
            Jsoup.connect("https://www.youtube.com/channel/UC6vNHBFM5VLNF53CKycyNZw/videos")
                .get()
        val elementsByTag = get.getElementsByTag("script")
        val prefix = "[{\"gridVideoRenderer"

        val first = elementsByTag.first { it.data().contains(prefix) }
        val startIndex = first.data().indexOf(prefix)
        val endIndex = first.data().indexOf(",\"continuations\"")
        val substring = first.data().substring(startIndex, endIndex)
        val items = Gson().fromJson<List<VideoResponse>>(substring)
        println("items count = ${items.count()}")
    }

    object SchedulerProvider {
        fun io(): Scheduler = Schedulers.trampoline()
        fun ui(): Scheduler = Schedulers.trampoline()
    }
}
