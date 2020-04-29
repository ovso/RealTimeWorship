package io.github.ovso.worship

import io.github.ovso.worship.data.network.ServiceLocator
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryTest {
    @Test
    fun `레파지토리 테스트`() {
        val provideTasksRepository = ServiceLocator.provideTasksRepository()
        println("addition_isCorrect = $provideTasksRepository")

    }
}
