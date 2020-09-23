package io.github.ovso.worship.view.ui.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toHistoryModels
import io.github.ovso.worship.data.view.HistoryModel
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber

class HistoryViewModel(
  private val owner: LifecycleOwner,
  private val repository: TasksRepository,
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<HistoryModel>>()
  val items = _items

  init {
    reqHistory()
  }

  private fun reqHistory() {
    repository.getHistories().observe(owner, {
      it.toHistoryModels()
        .subscribeOn(SchedulerProvider.io())
        .subscribe({ models ->
          _items.postValue(models)
        }, { t -> Timber.e(t) })
        .addTo(compositeDisposable)
    })
  }

}
