package com.ryan.poster_app_api_wrapper

import AnalyticsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

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
}
