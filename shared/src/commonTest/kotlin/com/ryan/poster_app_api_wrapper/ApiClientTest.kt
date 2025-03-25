package com.ryan.poster_app_api_wrapper

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertNotNull

class ApiClientTest {
    private val apiClient = ApiClient();

    @Test
    fun testAnalyticsData() {
        runBlocking {
            val data = apiClient.getAnalyticsData()
            assertNotNull(data, "response should not be null")
        }
    }
}
