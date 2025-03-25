package com.ryan.poster_app_api_wrapper

import io.ktor.client.engine.*

expect fun httpClientEngineFactory(): HttpClientEngineFactory<*>
