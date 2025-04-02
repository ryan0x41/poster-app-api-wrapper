package com.ryan.poster_app_api_wrapper

import AnalyticsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ErrorResponse(val error: String)

open class ApiClient(private var authToken: String? = null, private var authenticatedUser: AuthenticatedUser? = null) {
    private val client = HttpClient(httpClientEngineFactory()) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        // global error handling
        HttpResponseValidator {
            validateResponse { response ->
                // if status code is not 2**
                if (!response.status.isSuccess()) {
                    // read raw
                    val errorText = response.bodyAsText()
                    // attempt decode from json payload
                    val errorMessage = try {
                        Json { ignoreUnknownKeys = true }
                            .decodeFromString(ErrorResponse.serializer(), errorText)
                            .error
                    } catch (e: Exception) {
                        errorText
                    }
                    throw ResponseException(response, "HTTP ${response.status.value} error: $errorMessage")
                }
            }
        }

        // if authToken exists add it to all outgoing requests
        defaultRequest {
            authToken?.let { token ->
                header("Authorization", "Bearer $token")
            }
        }
    }

    // analytics endpoint
    suspend fun getAnalyticsData(): AnalyticsResponse {
        return client.get("https://api.poster-social.com/").body()
    }

    // SECTION: user

    // on success store token for future requests
    suspend fun login(usernameOrEmail: String, password: String): LoginResponse {
        val response: LoginResponse = client.post("https://api.poster-social.com/user/login") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("usernameOrEmail" to usernameOrEmail, "password" to password))
        }.body()
        authToken = response.token
        return response
    }

    suspend fun register(username: String, email: String, password: String): RegisterResponse {
        return client.post("https://api.poster-social.com/user/register") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("username" to username, "email" to email, "password" to password))
        }.body()
    }

    suspend fun auth(): AuthResponse {
        val response: AuthResponse = client.get("https://api.poster-social.com/user/auth").body()
        authenticatedUser = response.user
        return response
    }

    suspend fun getAuthenticatedUser(): AuthenticatedUser? {
        return authenticatedUser
    }

    suspend fun getUserProfileById(userId: String): UserProfileResponse {
        return client.get("https://api.poster-social.com/user/profile/${userId}").body()
    }

    suspend fun getUserProfile(username: String): UserProfileResponse {
        return client.get("https://api.poster-social.com/user/${username}").body()
    }

    // SECTION: chat

    suspend fun startConversation(participants: List<String>): ConversationResponse {
        return client.post("https://api.poster-social.com/conversation/create") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("participants" to participants))
        }.body()
    }

    suspend fun deleteConversation(conversationId: String): DeleteResponse {
        return client.delete("https://api.poster-social.com/conversation/delete/$conversationId").body()
    }

    suspend fun sendMessage(conversationId: String, content: String): MessageResponse {
        return client.post("https://api.poster-social.com/message/send") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("conversationId" to conversationId, "content" to content))
        }.body()
    }

    suspend fun sendTyping(conversationId: String): TypingResponse {
        return client.patch("https://api.poster-social.com/message/typing/$conversationId").body()
    }

    suspend fun getConversations(): ConversationsResponse {
        return client.get("https://api.poster-social.com/conversation/all").body()
    }

    suspend fun getMessageThread(conversationId: String): MessageThreadResponse {
        return client.get("https://api.poster-social.com/message/thread/$conversationId").body()
    }

    // SECTION: posts

    suspend fun getHomeFeed(): HomeFeed {
        return client.get("https://api.poster-social.com/user/feed/1").body()
    }
}
