package com.example.first_app.remote.dto.auth

import io.ktor.http.Headers
import kotlinx.serialization.Serializable


@Serializable
data class AuthResponse(
    val success: Boolean,
    val error: Boolean,
    val body: String
)

@Serializable
data class TestResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean

)


@Serializable
data class AuthResponseWithHeaders(
    val response: AuthResponse,
    val headers: Map<String, String>?,
    val sessionId: String?,
    val status: Int
)