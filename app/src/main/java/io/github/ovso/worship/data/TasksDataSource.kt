package io.github.ovso.worship.data

import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Single
import org.jsoup.nodes.Document

interface TasksDataSource {

    fun getChannels(queryMap: HashMap<String, String>): Single<JsonElement>

    fun getVideos(channelId: String): Single<Document>
}
