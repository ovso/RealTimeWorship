package io.github.ovso.worship.data.network

class SearchRepository(baseUrl: String = BASE_URL) :
    BaseRepository<SearchService>(
        baseUrl,
        SearchService::class.java
    ) {
}
