package io.github.ovso.worship.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

  @Provides
  fun provideTestClass(): TestClass {
    return TestClass()
  }

  @Provides
  fun provideLifecycleOwner(f: Fragment): LifecycleOwner {
    return f.requireActivity()
  }

}

class TestClass {

}
