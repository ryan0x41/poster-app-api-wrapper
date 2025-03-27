package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.Serializable

@Serializable
class ConversationsResponse(private val jsonData: JsonElement) {
    val data: JsonElement
        get() = jsonData
}
