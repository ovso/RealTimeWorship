package io.github.ovso.worship.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.network.model.VideoResponse
import io.github.ovso.worship.extensions.fromJson
import io.github.ovso.worship.view.base.DisposableViewModel
import org.jsoup.Jsoup
import timber.log.Timber


class MainViewModel(private val tasksRepository: TasksRepository) : DisposableViewModel() {

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

        Thread {
            val get =
                Jsoup.connect("https://www.youtube.com/channel/UC6vNHBFM5VLNF53CKycyNZw/videos")
                    .get()
            val elementsByTag = get.getElementsByTag("script")
            val prefix = "[{\"gridVideoRenderer"
            elementsByTag.forEach {
                if (it.data().contains(prefix)) {
                    val startIndex = it.data().indexOf(prefix)
                    val endIndex = it.data().indexOf(",\"continuations\"")
                    val subSequence = it.data().substring(startIndex, endIndex)
                    val fromJson1 = Gson().fromJson<List<VideoResponse>>(subSequence)
                    Timber.d("size = ${fromJson1.size}")
                }
            }
        }.start()
    }

    fun onClick(id: Int) {
        Timber.i("id = $id")
    }
}
