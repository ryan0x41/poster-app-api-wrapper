package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val message: String,
    val user: AuthenticatedUser,
    val userCookie: String
)

@Serializable
data class AuthenticatedUser(
    val id: String,
    val username: String,
    val email: String,
    val accountCreation: Long,
    val profileImageUrl: String,
    val isAdmin: Boolean,
    val warnings: Int = 0,
    val spotifyLinked: Boolean
)
