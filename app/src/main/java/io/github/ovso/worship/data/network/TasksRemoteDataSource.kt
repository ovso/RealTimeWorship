package io.github.ovso.worship.data.network

import com.google.gson.JsonElement
import io.github.ovso.worship.data.TasksDataSource
import io.reactivex.rxjava3.core.Single

class TasksRemoteDataSource : TasksDataSource {

    private val searchApi by lazy { Api(BASE_URL).create<SearchService>() }

    override fun getChannels(queryMap: HashMap<String, String>): Single<JsonElement> {
        return searchApi.channels(queryMap)
    }
}
