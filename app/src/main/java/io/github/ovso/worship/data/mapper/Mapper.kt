package io.github.ovso.worship.data.mapper

interface Mapper<in T, out K> {
    abstract fun toModels(response: T): K
}
