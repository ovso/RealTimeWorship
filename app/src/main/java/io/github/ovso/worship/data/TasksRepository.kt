package io.github.ovso.worship.data

import com.google.gson.JsonElement
import io.github.ovso.worship.data.network.TasksRemoteDataSource
import io.reactivex.rxjava3.core.Single
import org.jsoup.nodes.Document

class TasksRepository(private val remoteDataSource: TasksRemoteDataSource) : TasksDataSource {
    override fun getChannels(queryMap: HashMap<String, String>): Single<JsonElement> {
        return remoteDataSource.getChannels(queryMap)
    }

    override fun getVideos(channelId: String): Single<Document> {
        return remoteDataSource.getVideos(channelId)
    }
}
