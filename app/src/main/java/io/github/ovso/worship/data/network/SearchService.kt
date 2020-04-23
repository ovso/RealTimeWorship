package io.github.ovso.worship.data.network

import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchService {
    @GET("youtube/v3/channels")
    fun channels(@QueryMap queryMap: HashMap<String, String>): Single<JsonElement>
}
