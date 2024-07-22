package com.example.first_app.remote.api.check

import com.example.first_app.remote.api.auth.AuthService
import com.example.first_app.remote.api.auth.AuthServiceImp
import com.example.first_app.remote.dto.check.CheckRequest
import com.example.first_app.remote.dto.check.CheckResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface CheckService {
    suspend fun getCheck(checkRequest: CheckRequest): CheckResponse?

    companion object {
        fun create(): CheckService {
            return CheckServiceImp(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )

        }
    }
}