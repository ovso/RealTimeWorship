package io.github.ovso.worship.app

import android.app.Application
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.remote.ServiceLocator

class App : Application() {

    val taskRepository: TasksRepository
        get() = ServiceLocator.provideTasksRepository()

    override fun onCreate() {
        super.onCreate()
        LibraryInit(this)
    }

}
