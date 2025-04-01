package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.Serializable

@Serializable
data class ConversationResponse(
    val message: String,
    val conversations: List<PosterConversation> = emptyList()
)

@Serializable
data class PosterConversation(
    val conversationId: String,
    val participants: List<String>,
    val createdAt: String
)
