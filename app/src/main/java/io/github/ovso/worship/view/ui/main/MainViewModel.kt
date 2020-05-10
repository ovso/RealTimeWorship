package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.network.response.VideoResponse
import io.github.ovso.worship.utils.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import timber.log.Timber


class MainViewModel(
    private val tasksRepository: TasksRepository,
    private val defaultArgs: Bundle? = null
) : DisposableViewModel() {

    private val _items = MutableLiveData<List<MainItem>>()
    fun getItems(): LiveData<List<MainItem>> = _items

    init {
        Timber.d(defaultArgs.toString())
        defaultArgs?.let {
            fun onFailure(t: Throwable) {
                println(t.message)
            }

            fun onSuccess(items: List<VideoResponse>) {
                println("items size = ${items.count()}")
            }

            val channelId = it.getString("channel_id")
            tasksRepository.getVideos(channelId!!)
                .map {

                    it
                }
                .subscribeOn(SchedulerProvider.io())
                .observeOn(SchedulerProvider.ui())
                .subscribe(::onSuccess, ::onFailure)
        }
//        tasksRepository.getVideos("UC6vNHBFM5VLNF53CKycyNZw")
/*
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
*/
    }

    fun onClick(id: Int) {
        Timber.i("id = $id")
    }
}