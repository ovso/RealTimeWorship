@file:Suppress("UNUSED_PARAMETER")

package io.github.ovso.worship.view.ui.player

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.local.model.HistoryEntity
import io.github.ovso.worship.data.toBookmarkEntity
import io.github.ovso.worship.data.toHistoryEntity
import io.github.ovso.worship.data.view.PlayerModel
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.plusAssign
import timber.log.Timber

class PlayerViewModel(
  private val repository: TasksRepository,
  arguments: Bundle?,
  intent: Intent? = null,
  private val owner: LifecycleOwner
) : DisposableViewModel() {
  private var playerModel = MutableLiveData<PlayerModel>()
  private val _videoId = MutableLiveData<String>()
  val videoId: LiveData<String> = _videoId
  private val _desc = MutableLiveData<String>()
  val desc: LiveData<String> = _desc
  private val _thumbnail = MutableLiveData<String>()
  val thumbnail: LiveData<String> = _thumbnail
  val isBookmarkSelected = ObservableBoolean(false)

  var second = 0F

  init {
    observe()
    handleArgs(arguments)
  }

  private fun observe() {
    playerModel.observe(owner, {
      _videoId.value = it.videoId
      _desc.value = it.title
      _thumbnail.value = it.thumbnail
      checkBookmark(it.videoId)
      checkHistory(it.videoId)
    })
  }

  private fun handleArgs(arguments: Bundle?) {
    arguments?.getParcelable<PlayerModel>("model")?.let {
      playerModel.value = it
    }
  }

  private fun checkHistory(videoId: String) {

    fun addHistory(history: HistoryEntity) {
      Thread { repository.addHistory(history) }.start()
    }

    repository.getHistory(videoId).observe(owner, Observer {
      if (it == null) {
        playerModel.value?.toHistoryEntity()?.let { historyEntity ->
          addHistory(historyEntity)
        }
      }
    })
  }

  private fun checkBookmark(videoId: String) {
    repository.getBookmark(videoId).observe(owner, Observer {
      it?.let {
        isBookmarkSelected.set(true)
      }
    })
  }

  fun onBookmarkClick() {
    isBookmarkSelected.set(isBookmarkSelected.get().not())
    playerModel.value?.let {
      when (isBookmarkSelected.get()) {
        true -> addBookmark(it)
        false -> delBookmark(it)
      }
    }
  }

  private fun addBookmark(it: PlayerModel) {
    compositeDisposable += Single.fromCallable {
      repository.addBookmark(it.toBookmarkEntity())
    }.subscribeOn(SchedulerProvider.io())
      .subscribe({}, { Timber.e(it) })
  }

  private fun delBookmark(it: PlayerModel) {
    fun onSuccess(result: Int) {
      Timber.d("onSuccess = $result")
    }

    fun onFailure(t: Throwable) {
      Timber.d("onFailure = ${t.printStackTrace()}")
    }

    compositeDisposable += Single.fromCallable {
      repository.delBookmark(it.toBookmarkEntity())
    }.subscribeOn(SchedulerProvider.io())
      .subscribe(::onSuccess, ::onFailure)
  }
}
