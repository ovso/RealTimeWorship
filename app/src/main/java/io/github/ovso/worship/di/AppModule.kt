package io.github.ovso.worship.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideTaskRepository(app: Application): TestClass {
    return TestClass()
  }
}
