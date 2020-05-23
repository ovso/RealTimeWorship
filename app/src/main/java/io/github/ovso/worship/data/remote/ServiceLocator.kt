package io.github.ovso.worship.data.remote

import android.content.Context
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.local.TasksLocalDataSource

object ServiceLocator {

    @Volatile
    var tasksRepository: TasksRepository? = null

    fun provideTasksRepository(context: Context): TasksRepository {
        synchronized(this) {
            return tasksRepository ?: createTasksRepository(context)
        }
    }

    private fun createTasksRepository(context: Context): TasksRepository {
        val newRepo = TasksRepository(TasksRemoteDataSource(), TasksLocalDataSource(context))
        tasksRepository = newRepo
        return newRepo
    }
}
