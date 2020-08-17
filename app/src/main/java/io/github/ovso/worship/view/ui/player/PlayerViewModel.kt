@file:Suppress("UNUSED_PARAMETER")

package io.github.ovso.worship.view.ui.player

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import io.github.ovso.worship.data.TasksRepository
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
  private val playerModel: MutableLiveData<PlayerModel> = MutableLiveData()

  val videoId = Transformations.map(playerModel) { model -> model.videoId }
  val title = Transformations.map(playerModel) { model -> model.title }
  val thumbnail = Transformations.map(playerModel) { model -> model.thumbnail }

  val isBookmarkSelected = MutableLiveData(false)

  var second = 0F

  init {
    handleIntent(intent)
  }

  private fun handleIntent(intent: Intent?) {
    intent?.getParcelableExtra<PlayerModel>("model")?.let {
      playerModel.value = it
      observeBookmark(it.videoId)
      historyExists(it.videoId)
    }
  }

  private fun historyExists(videoId: String) {
    repository.getHistory(videoId).observe(owner, Observer {
      if (it == null) {
        playerModel.value?.let { pModel ->
          Thread { repository.addHistory(pModel.toHistoryEntity()) }.start()
        }
      }
    })
  }

  private fun observeBookmark(videoId: String) {
    repository.getBookmark(videoId).observe(owner, Observer {
      it?.let {
        isBookmarkSelected.value = true
      }
    })
  }

  fun onBookmarkClick() {
    isBookmarkSelected.value = isBookmarkSelected.value!!.not()
    playerModel.value?.let {
      when (isBookmarkSelected.value!!) {
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
