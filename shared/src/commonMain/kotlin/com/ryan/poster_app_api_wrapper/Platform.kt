package com.ryan.poster_app_api_wrapper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform