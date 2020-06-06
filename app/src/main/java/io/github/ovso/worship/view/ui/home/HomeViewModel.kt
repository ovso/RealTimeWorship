package io.github.ovso.worship.view.ui.home

import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.view.ChurchModel
import io.github.ovso.worship.data.toChurchModels
import io.github.ovso.worship.utils.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.plusAssign

class HomeViewModel(private val repository: TasksRepository) : DisposableViewModel() {

  val items = MutableLiveData<List<ChurchModel>>()

  init {
    reqChurches()
  }

  private fun reqChurches() {

    fun onSuccess(churches: List<ChurchModel>) {
      items.postValue(churches)
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
