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
    //login testing method
    fun testLogin(username: String, password: String): LoginResponse {
        return runBlocking {
            apiClient.login(username, password)
        }
    }
    //authentication test method
    fun testAuth(): AuthResponse {
        return runBlocking {
            apiClient.auth()
        }
    }
    //register test method
    fun testRegister() {
        runBlocking {
            val data = apiClient.register("newaccount32131213", "emails@something.com", "Hello@123")

            println("Message: ${data.message}")
            println("User: ${data.user}")
        }
    }
    //start conversation test method
    fun testStartConversation(participantIds: List<String>) {
        runBlocking {
            val data = apiClient.startConversation(participantIds)
            println("startConversation message: ${data.message}")
        }
    }
    //conversation test method
    fun testGetConversations() {
        runBlocking {
            val data = apiClient.getConversations()
            println("data: $data")
        }
    }
    //user profile id test method
    fun testGetUserProfileById(userId: String) {
        runBlocking {
            val data = apiClient.getUserProfileById(userId)
            println("data: $data")
        }
    }
    //user profile username test method
    fun testGetUserProfileByUsername(username: String): FullUserProfileResponse {
        return runBlocking {
            apiClient.getUserProfileById(username)
        }
    }
    //user home feed test method
    fun testGetUserHomeFeed() {
        return runBlocking {
            apiClient.getHomeFeed()
        }
    }
    //user authentication test method
    fun testGetAuthenticatedUser() {
        return runBlocking {
            val data = apiClient.getAuthenticatedUser()
            println(data)
        }
    }

    @Test
    fun testFullFlow(): Unit {
        runBlocking {
            // Step 1: Login as "test2"
            val loginResponse = apiClient.login("test2", "Hello@123")
            println("Login Response: $loginResponse")
            assertNotNull(loginResponse, "Login response should not be null")

            // Step 2: Authenticate to get current user info
            val authResponse = apiClient.auth()
            println("Auth Response: $authResponse")
            assertNotNull(authResponse, "Auth response should not be null")

            val myUserId = authResponse.user.id
            println("Authenticated User ID: $myUserId")

            // Step 3: Retrieve the home feed
            val homeFeed = apiClient.getHomeFeed()
            println("Home Feed: $homeFeed")
            assertNotNull(homeFeed, "Home feed should not be null")

            // Step 4: Retrieve the authenticated user details
            val authenticatedUser = apiClient.getAuthenticatedUser()
            println("Authenticated User: $authenticatedUser")
            assertNotNull(authenticatedUser, "Authenticated user should not be null")

            // Step 5: Fetch the user profile using the authenticated user's ID
            val userProfileResponse = apiClient.getUserProfileById(myUserId)
            println("User Profile: $userProfileResponse")
            assertNotNull(userProfileResponse, "User profile should not be null")
        }
    }
}
