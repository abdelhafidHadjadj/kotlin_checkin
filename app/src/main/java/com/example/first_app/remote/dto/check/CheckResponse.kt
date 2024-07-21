package com.example.first_app.remote.dto.check

import kotlinx.serialization.Serializable


@Serializable
data class CheckResponse(
    val firstname: String,
    val lastname: String,
    val role: String,
)
