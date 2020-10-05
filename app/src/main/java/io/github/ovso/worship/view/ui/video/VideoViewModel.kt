package io.github.ovso.worship.view.ui.video

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.dto.VideoDto
import io.github.ovso.worship.data.remote.TasksRemoteDataSource
import io.github.ovso.worship.data.toVideoModels
import io.github.ovso.worship.data.view.VideoModel
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber

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
    defaultArgs?.getParcelable<VideoDto>("video_dto")?.let {
      _title.value = it.title
      reqVideos(it.id, it.category)
    }
  }

  private fun reqVideos(id: String, category: String) {
    _isLoading.value = true
    fun onFailure(t: Throwable) {
      Timber.e(t)
      _isLoading.value = false
    }

    fun onSuccess(items: List<VideoModel>) {
      Timber.d("items size = ${items.count()}")
      _items.value = items
      _isLoading.value = false
    }

    val categoryId = when (category) {
      "channel" -> TasksRemoteDataSource.CategoryId.ChannelId(id)
      else -> TasksRemoteDataSource.CategoryId.PlayListId(id)
    }
    tasksRepository.videos(categoryId)
      .map { response -> response.toVideoModels() }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure).addTo(compositeDisposable)
  }

}
