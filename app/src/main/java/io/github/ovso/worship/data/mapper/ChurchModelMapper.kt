package io.github.ovso.worship.data.mapper

import io.github.ovso.worship.data.local.model.ChurchEntity
import io.github.ovso.worship.data.view.ChurchModel
import io.reactivex.rxjava3.kotlin.toObservable

object ChurchModelMapper {
  fun fromEntity(entities: List<ChurchEntity>): List<ChurchModel> {
    return entities.toObservable().map {
        ChurchModel(
            title = it.title,
            channelId = it.channelId
        )
    }.toList().blockingGet()
  }
}
