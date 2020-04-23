@file:Suppress("NonAsciiCharacters")

package io.github.ovso.worship

import io.github.ovso.worship.data.network.SearchRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class YoutubeApiTest {
    private val repository by lazy {
        SearchRepository()
    }

    @Test
    fun `유투브 채널 정보를 받아오기`() {
        assertEquals(4, 2 + 2)
        repository.api()
    }
}
