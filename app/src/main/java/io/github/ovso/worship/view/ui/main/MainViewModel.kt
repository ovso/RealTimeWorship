package io.github.ovso.worship.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.view.base.DisposableViewModel

class MainViewModel : DisposableViewModel() {

    private val _items = MutableLiveData<List<MainItem>>()
    val items: LiveData<List<MainItem>> = _items

    init {
        _items.postValue(
            listOf(
                MainItem(
                    churchName = "삼일교회",
                    videoId = "hQbWYg02GX0"
                ),
                MainItem(
                    churchName = "분당우리교회",
                    videoId = "oGhmlQvPk3s"
                )
            )
        )
    }
}
