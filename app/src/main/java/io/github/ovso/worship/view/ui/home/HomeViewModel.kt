package io.github.ovso.worship.view.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toChurchModels
import io.github.ovso.worship.data.view.HomeItemModel
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.plusAssign

class HomeViewModel @ViewModelInject constructor(
  private val repository: TasksRepository,
  @Assisted private val savedStateHandle: SavedStateHandle,
) :
  DisposableViewModel() {

  val items = MutableLiveData<List<HomeItemModel>>()

  init {
    reqChurches()
  }

  private fun reqChurches() {

    fun onSuccess(homeItems: List<HomeItemModel>) {
      items.postValue(homeItems)
    }

    fun onFailure(t: Throwable) {
      t.printStackTrace()
    }

    compositeDisposable += repository.churches()
      .map { it.toChurchModels() }
      .map { it ->
        it.sortedBy {
          it.title
        }
      }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }
}
