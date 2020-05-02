package io.github.ovso.worship.data.network

import com.google.gson.JsonElement
import io.github.ovso.worship.data.TasksDataSource
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class TasksRemoteDataSource : TasksDataSource {

    private val searchApi by lazy { Api(BASE_URL).create<SearchService>() }

    override fun getChannels(queryMap: HashMap<String, String>): Single<JsonElement> {
        return searchApi.channels(queryMap)
    }

    override fun getVideos(channelId: String): Single<Document> {
        return Single.fromCallable {
            val sb = StringBuilder()
                .append(BASE_URL_YOUTUBE)
                .append("/channel/")
                .append(channelId)
                .append("videos")
            Jsoup.connect(sb.toString()).get()
        }
    }
}
