package io.github.ovso.worship

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.github.ovso.worship.data.local.TasksLocalDataSource
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
  @Test
  fun useAppContext() {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertEquals("io.github.ovso.worship.dev", appContext.packageName)
  }

  @Test
  fun localDataSourceTest() {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    TasksLocalDataSource(appContext)
      .churches()
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe({
        assertTrue(true)
      }, {
        assertTrue(false)
      })
  }

  object SchedulerProvider {
    fun io(): Scheduler = Schedulers.trampoline()
    fun ui(): Scheduler = Schedulers.trampoline()
  }

}
