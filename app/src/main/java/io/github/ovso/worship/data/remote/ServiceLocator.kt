package io.github.ovso.worship.data.remote

import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.local.TasksLocalDataSource

object ServiceLocator {

    @Volatile
    var tasksRepository: TasksRepository? = null

    fun provideTasksRepository(): TasksRepository {
        synchronized(this) {
            return tasksRepository ?: createTasksRepository()
        }
    }

    private fun createTasksRepository(): TasksRepository {
        val newRepo = TasksRepository(TasksRemoteDataSource(), TasksLocalDataSource())
        tasksRepository = newRepo
        return newRepo
    }
}
