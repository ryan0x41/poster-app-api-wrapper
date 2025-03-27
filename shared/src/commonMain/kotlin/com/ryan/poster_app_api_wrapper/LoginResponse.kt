package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val message: String,
    val token: String
)
