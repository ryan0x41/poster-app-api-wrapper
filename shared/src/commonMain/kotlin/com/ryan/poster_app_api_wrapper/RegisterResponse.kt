package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val message: String,
    val user: RegisterUser
)

@Serializable
data class RegisterUser(
    val id: String,
    val username: String,
    val email: String
)
