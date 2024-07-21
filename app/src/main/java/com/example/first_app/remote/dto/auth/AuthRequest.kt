package com.example.first_app.remote.dto.auth

import kotlinx.serialization.Serializable


@Serializable
data class AuthRequest(
    val uuid: String
)

