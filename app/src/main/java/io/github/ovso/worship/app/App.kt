package io.github.ovso.worship.app

import android.app.Application
import androidx.room.Room
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.local.AppDatabase
import io.github.ovso.worship.data.remote.ServiceLocator
import timber.log.Timber

class App : Application() {

  val taskRepository: TasksRepository
    get() = ServiceLocator.provideTasksRepository(this)
  lateinit var database: AppDatabase

  override fun onCreate() {
    super.onCreate()
    LibraryInit(this)
    Timber.plant(Timber.DebugTree())
    database = Room.databaseBuilder(this, AppDatabase::class.java, "database").build()
  }
}
