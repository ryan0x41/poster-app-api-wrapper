package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.Serializable

@Serializable
data class Conversation(
    val conversationId: String,
    val participants: List<String>,
    val createdAt: String
)

@Serializable
data class ConversationsResponse(
    val message: String,
    val conversations: List<Conversation>
)
