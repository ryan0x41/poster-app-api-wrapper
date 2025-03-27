package com.ryan.poster_app_api_wrapper

import AnalyticsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.ServerResponseException

class ApiClient {
    // create instance of HttpClient with platform specific engine
    private val client = HttpClient(httpClientEngineFactory()) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun getAnalyticsData(): AnalyticsResponse {
        return client.get("https://api.poster-social.com/").body()
    }

    // SECTION: user

    // this is the most important function, so ill explain every bit, even though its not that complex
    // - usernameOrEmail and password are both passed as String to the login function, the login
    //   function is defined to return a LoginResponse object which is json serialisable
    // - the content type is set as the api intends, and the body of the post request is mapped
    //   correctly using setBody, key is "usernameOrEmail" the value is the object usernameOrEmail
    // - this post request is returned, but we make sure to return the body of the login response

    // this is all surrounded in a try catch, if we get any response status code which is not 200
    // we check if its an internal server error, which shouldnt really happen, and we throw an
    // exception with some extra information, we can handle this when calling login later
    // and if its not 500 we just throw that to

    // some things to note
    // - function is async by using suspend
    // - types are strict, unlike javascript
    // - we can probably use this general structure throughout the other requests we make to the api
    suspend fun login(usernameOrEmail: String, password: String): LoginResponse {
        try {

            return client.post("https://api.poster-social.com/user/login") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("usernameOrEmail" to usernameOrEmail, "password" to password))
            }.body<LoginResponse>()

        } catch (e: ServerResponseException) {

            if (e.response.status == HttpStatusCode.InternalServerError) {
                throw Exception("error 500, please try again later")
            }

            throw e
        }
    }

    suspend fun register(username: String, email: String, password: String): RegisterResponse {
        try {
            return client.post("https://api.poster-social.com/user/register") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("username" to username, "email" to email, "password" to password))
            }.body<RegisterResponse>()
        } catch (e: ServerResponseException) {
            if(e.response.status == HttpStatusCode.InternalServerError) {
                throw Exception("error 500, please try again later")
            }

            throw e
        }
    }

    // SECTION: chat

    // start conversation with a list of participants
    suspend fun startConversation(participants: List<String>): ConversationResponse {
        return client.post("https://api.poster-social.com/conversation/create") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("participants" to participants))
        }.body()
    }

    // delete a conversation given an id
    suspend fun deleteConversation(conversationId: String): DeleteResponse {
        return client.delete("https://api.poster-social.com/conversation/delete/$conversationId")
            .body()
    }

    // send a message to a conversation given conversationId and some content
    suspend fun sendMessage(conversationId: String, content: String): MessageResponse {
        return client.post("https://api.poster-social.com/message/send") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("conversationId" to conversationId, "content" to content))
        }.body()
    }

    // send typing alert to participants in the conversation
    suspend fun sendTyping(conversationId: String): TypingResponse {
        return client.patch("https://api.poster-social.com/message/typing/$conversationId")
            .body()
    }

    // retrieve all conversations for user
    suspend fun getConversations(): ConversationsResponse {
        return client.get("https://api.poster-social.com/conversation/all")
            .body()
    }

    // retrieve message thread for a conversation
    suspend fun getMessageThread(conversationId: String): MessageThreadResponse {
        return client.get("https://api.poster-social.com/message/thread/$conversationId")
            .body()
    }
}
