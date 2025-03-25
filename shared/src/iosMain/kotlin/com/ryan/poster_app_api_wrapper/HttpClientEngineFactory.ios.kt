package com.ryan.poster_app_api_wrapper

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.*

actual fun httpClientEngineFactory(): HttpClientEngineFactory<*> = Darwin
