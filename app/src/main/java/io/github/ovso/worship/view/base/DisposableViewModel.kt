package io.github.ovso.worship.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class DisposableViewModel : ViewModel() {

  val compositeDisposable = CompositeDisposable()
  val legacyCompositeDisposable = io.reactivex.disposables.CompositeDisposable()
  private val isLoading = MutableLiveData(false)
  override fun onCleared() {
    compositeDisposable.clear()
    super.onCleared()
  }

  protected fun showLoading() {
    isLoading.postValue(true)
  }

  protected fun hideLoading() {
    isLoading.postValue(false)
  }
}
