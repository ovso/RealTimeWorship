package io.github.ovso.worship.data.network

import io.github.ovso.worship.data.TasksRepository

object ServiceLocator {

    @Volatile
    var tasksRepository: TasksRepository? = null

    fun provideTasksRepository(): TasksRepository {
        synchronized(this) {
            return tasksRepository ?: createTasksRepository()
        }
    }

    private fun createTasksRepository(): TasksRepository {
        val newRepo = TasksRepository(TasksRemoteDataSource())
        tasksRepository = newRepo
        return newRepo
    }
}
