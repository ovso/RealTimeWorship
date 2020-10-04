package io.github.ovso.worship.view.ui.video

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.remote.TasksRemoteDataSource
import io.github.ovso.worship.data.toVideoModels
import io.github.ovso.worship.data.view.VideoModel
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.addTo

class VideoViewModel(
  private val tasksRepository: TasksRepository,
  defaultArgs: Bundle? = null
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<VideoModel>>()
  val items: LiveData<List<VideoModel>> = _items
  private val _isLoading = MutableLiveData(true)
  val isLoading: LiveData<Boolean> = _isLoading
  private val _title = MutableLiveData<String>()
  val title: LiveData<String> = _title

  init {
    defaultArgs?.let { args ->
      args.getString("channel_id")?.let { reqVideos(it) }
      args.getString("title")?.let { _title.value = it }
    }
  }

  private fun reqVideos(channelId: String) {
    _isLoading.value = true
    fun onFailure(t: Throwable) {
      println(t.message)
      _isLoading.value = false
    }

    fun onSuccess(items: List<VideoModel>) {
      println("items size = ${items.count()}")
      _items.value = items
      _isLoading.value = false
    }

    tasksRepository.videos(TasksRemoteDataSource.CategoryId.ChannelId(channelId))
      .map { response -> response.toVideoModels() }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure).addTo(compositeDisposable)
  }

}
