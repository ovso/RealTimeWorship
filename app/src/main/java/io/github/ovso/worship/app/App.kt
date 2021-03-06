package io.github.ovso.worship.app

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.local.AppDatabase
import io.github.ovso.worship.data.remote.ServiceLocator

@HiltAndroidApp
class App : Application() {

  val taskRepository: TasksRepository
    get() = ServiceLocator.provideTasksRepository(this)
  lateinit var database: AppDatabase

  override fun onCreate() {
    super.onCreate()
    LibraryInit(this)
    database = Room.databaseBuilder(
      this,
      AppDatabase::class.java,
      "database"
    ).build()
  }
}
