package com.ryan.poster_app_api_wrapper

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.*

actual fun httpClientEngineFactory(): HttpClientEngineFactory<*> = CIO
