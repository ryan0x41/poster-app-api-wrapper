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

            println("Server Hostname: ${data.server.hostname}")
            println("Server Platform: ${data.server.platform}")
            println("Server Architecture: ${data.server.arch}")
            println("Node Version: ${data.server.nodeVersion}")
            println("Memory Usage: ${data.server.memoryUsage}")
            println("Uptime: ${data.server.uptime}")
            println("Start Time: ${data.server.startTime}")

            assertNotNull(data, "response should not be null")
        }
    }

    @Test
    fun testLogin() {
        runBlocking {
            val data = apiClient.login("test2", "Hello@123")

            println("Message: ${data.message}")
            println("Token: ${data.token}")
        }
    }

    @Test
    fun testRegister() {
        runBlocking {
            val data = apiClient.register("newaccount3213213", "email@something.com", "Hello@123")

            println("Message: ${data.message}")
            println("User: ${data.user}")
        }
    }
}
