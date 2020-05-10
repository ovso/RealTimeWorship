package io.github.ovso.worship.data.network

import com.google.gson.Gson
import io.github.ovso.worship.data.TasksDataSource
import io.github.ovso.worship.data.network.response.VideoResponse
import io.github.ovso.worship.extensions.fromJson
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup

class TasksRemoteDataSource : TasksDataSource {

    override fun getVideos(channelId: String): Single<List<VideoResponse>> {
        return Single.fromCallable {
            val document = Jsoup.connect("https://www.youtube.com/channel/$channelId/videos").get()
            val scriptElements = document.getElementsByTag("script")
            val prefix = "[{\"gridVideoRenderer"
            val itemsElement = scriptElements.first { it.data().contains(prefix) }
            val startIndex = itemsElement.data().indexOf(prefix)
            val endIndex = itemsElement.data().indexOf(",\"continuations\"")
            val jsonArrayString = itemsElement.data().substring(startIndex, endIndex)
            Gson().fromJson<List<VideoResponse>>(jsonArrayString)
        }
    }
}
