package com.ryan.poster_app_api_wrapper

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertNotNull

class ApiClientTest {
    private val apiClient = ApiClient()

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

    fun testLogin(username: String, password: String): LoginResponse {
        return runBlocking {
            apiClient.login(username, password)
        }
    }

    fun testAuth(): AuthResponse {
        return runBlocking {
            apiClient.auth()
        }
    }

    fun testRegister() {
        runBlocking {
            val data = apiClient.register("newaccount32131213", "emails@something.com", "Hello@123")

            println("Message: ${data.message}")
            println("User: ${data.user}")
        }
    }

    fun testStartConversation(participantIds: List<String>) {
        runBlocking {
            val data = apiClient.startConversation(participantIds)
            println("startConversation message: ${data.message}")
        }
    }

    fun testGetConversations() {
        runBlocking {
            val data = apiClient.getConversations()
            println("data: $data")
        }
    }

    fun testGetUserProfileById(userId: String) {
        runBlocking {
            val data = apiClient.getUserProfileById(userId)
            println("data: $data")
        }
    }

    fun testGetUserProfileByUsername(username: String): UserProfileResponse {
        return runBlocking {
            apiClient.getUserProfileById(username)
        }
    }

    @Test
    fun testAll() {
        // Step 1: login as test2
        val loginResponse = testLogin("test2", "Hello@123")

        // Step 2: authenticate to get current user ID
        val authResponse = testAuth()
        val myUserId = authResponse.user.id
        println("Authenticated user ID: $myUserId")

        // Step 3: get user profile of 0xryan
        val ryanProfile = testGetUserProfileByUsername("0xryan")
        val ryanUserId = ryanProfile.user.id
        println("0xryan's user ID: $ryanUserId")

        // Step 4: start conversation with 0xryan
        val participantIds = listOf(ryanUserId)
        testStartConversation(participantIds)
    }
}
