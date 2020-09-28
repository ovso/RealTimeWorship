package io.github.ovso.worship.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.view.ui.bookmark.BookmarkViewModel

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

  @Suppress("UNCHECKED_CAST")
  @Provides
  fun provideHistoryViewModel(f: Fragment, repository: TasksRepository): BookmarkViewModel {
    return ViewModelProvider(f, object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookmarkViewModel(f, repository) as T
      }
    })[BookmarkViewModel::class.java]
  }
}
