package io.github.ovso.worship.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class DisposableViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>? = _isLoading
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun showLoading() {
        _isLoading.postValue(true)
    }

    protected fun hideLoading() {
        _isLoading.postValue(false)
    }
}
