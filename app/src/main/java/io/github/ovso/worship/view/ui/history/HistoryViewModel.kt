package io.github.ovso.worship.view.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toHistoryModels
import io.github.ovso.worship.data.view.HistoryModel
import io.github.ovso.worship.utils.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import timber.log.Timber

class HistoryViewModel(owner: SavedStateRegistryOwner, repository: TasksRepository) :
  DisposableViewModel() {

  private val _items = MutableLiveData<List<HistoryModel>>()
  val items = _items

  init {
    repository.getHistories().observe(owner, Observer {
      compositeDisposable += it.toHistoryModels()
        .subscribeOn(SchedulerProvider.io())
        .subscribe({ models ->
          _items.postValue(models)
        }, { t -> Timber.e(t) })
    })
  }

}
