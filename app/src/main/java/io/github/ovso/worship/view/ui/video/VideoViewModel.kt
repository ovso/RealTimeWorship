package io.github.ovso.worship.view.ui.video

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toVideoModels
import io.github.ovso.worship.data.view.VideoModel
import io.github.ovso.worship.utils.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import timber.log.Timber

class VideoViewModel(
  private val tasksRepository: TasksRepository,
  defaultArgs: Bundle? = null
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<VideoModel>>()
  val items: LiveData<List<VideoModel>> = _items
  private val _isLoading = MutableLiveData<Boolean>(true)
  val isLoading = _isLoading

  init {
    defaultArgs?.let {
      fun onFailure(t: Throwable) {
        println(t.message)
        _isLoading.value = false
      }

      fun onSuccess(items: List<VideoModel>) {
        println("items size = ${items.count()}")
        _items.value = items
        _isLoading.value = false
      }

      val channelId = it.getString("channel_id")
      tasksRepository.videos(channelId!!)
        .map { response -> response.toVideoModels() }
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
