package io.github.ovso.worship.data.network

import android.content.Context
import io.github.ovso.worship.data.TasksRepository

object ServiceLocator {

    @Volatile
    var tasksRepository: TasksRepository? = null

    fun provideTasksRepository(context: Context): TasksRepository {
        synchronized(this) {
            return tasksRepository ?: createTasksRepository(context)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun createTasksRepository(context: Context): TasksRepository {
        val newRepo = TasksRepository(TasksRemoteDataSource())
        tasksRepository = newRepo
        return newRepo
    }
}
