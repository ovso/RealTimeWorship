package io.github.ovso.worship.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.local.TasksLocalDataSource
import io.github.ovso.worship.data.remote.TasksRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

  @Provides
  @Singleton
  fun provideTasksRepository(
    local: TasksLocalDataSource,
    remote: TasksRemoteDataSource
  ): TasksRepository {
    return TasksRepository(
      remote, local
    )
  }

  @Provides
  @Singleton
  fun provideTaskRemoteDataSource(): TasksRemoteDataSource {
    return TasksRemoteDataSource()
  }

  @Provides
  @Singleton
  fun provideTasksLocalDataSource(app: Application): TasksLocalDataSource {
    return TasksLocalDataSource(app)
  }
}
