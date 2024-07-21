package com.example.first_app.remote.dto.auth

import kotlinx.serialization.Serializable


@Serializable
data class AuthResponse(
    val success: Boolean,
    val error: Boolean,
    val body: String
)
