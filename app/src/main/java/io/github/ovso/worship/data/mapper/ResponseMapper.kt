package io.github.ovso.worship.data.mapper

interface ResponseMapper<in T, out K> {
    abstract fun fromResponse(response: T): K
}
