package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.mapper.VideoResponseMapper
import io.github.ovso.worship.data.view.VideoModel
import io.github.ovso.worship.utils.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber


class MainViewModel(
    private val tasksRepository: TasksRepository,
    defaultArgs: Bundle? = null
) : DisposableViewModel() {

    private val _items = MutableLiveData<List<VideoModel>>()
    fun getItems(): LiveData<List<VideoModel>> = _items

    init {
        defaultArgs?.let {
            fun onFailure(t: Throwable) {
                println(t.message)
            }

            fun onSuccess(items: List<VideoModel>) {
                println("items size = ${items.count()}")
                _items.value = items
            }

            val channelId = it.getString("channel_id")
            tasksRepository.getVideos(channelId!!)
                .flatMapObservable { responses -> Observable.fromIterable(responses) }
                .map { response -> VideoResponseMapper.fromResponse(response) }
                .toList()
                .subscribeOn(SchedulerProvider.io())
                .observeOn(SchedulerProvider.ui())
                .subscribe(::onSuccess, ::onFailure)
        }
    }

    private fun clearList() {
        _items.value = listOf()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared()")
    }

    fun onClick(id: Int) {
        Timber.i("id = $id")
    }
}
