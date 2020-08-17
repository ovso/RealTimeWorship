package io.github.ovso.worship.view.ui.home

import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.view.HomeItemModel
import io.github.ovso.worship.data.toChurchModels
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.plusAssign

class HomeViewModel(private val repository: TasksRepository) : DisposableViewModel() {

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
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }
}
