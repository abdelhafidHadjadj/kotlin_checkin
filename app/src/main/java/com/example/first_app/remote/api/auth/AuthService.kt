package com.example.first_app.remote.api.auth

import com.example.first_app.remote.dto.auth.AuthRequest
import com.example.first_app.remote.dto.auth.AuthResponse
import com.example.first_app.remote.dto.auth.AuthResponseWithHeaders
import com.example.first_app.remote.dto.auth.TestResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.HttpCookies
import io.ktor.client.features.logging.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

interface AuthService {
    suspend fun login(authRequest: AuthRequest): AuthResponseWithHeaders?

    companion object {
        fun create(): AuthService {
            return AuthServiceImp(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                    install(HttpCookies) {
                        // Utilisez le stockage en mémoire pour les cookies
                        storage= AcceptAllCookiesStorage()
                    }
                }
            )

        }
    }

}





interface TestService {
    suspend fun test(uuid: String): TestResponse?

    companion object {
        fun create(): TestService {
            return TestImp(
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

