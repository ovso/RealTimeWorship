package io.github.ovso.worship.view.ui.history

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toHistoryModels
import io.github.ovso.worship.data.view.HistoryModel
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber

class HistoryViewModel @ViewModelInject constructor(
  private val repository: TasksRepository,
  @Assisted private val savedStateHandle: SavedStateHandle,
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<HistoryModel>>()
  val items = _items

  init {

/*
    repository.getHistories().observe(owner, {
      it.toHistoryModels()
        .subscribeOn(SchedulerProvider.io())
        .subscribe({ models ->
          _items.postValue(models)
        }, { t -> Timber.e(t) })
        .addTo(compositeDisposable)
    })
*/
  }

}
