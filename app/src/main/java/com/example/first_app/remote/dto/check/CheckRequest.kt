package com.example.first_app.remote.dto.check

import kotlinx.serialization.Serializable



@Serializable
data class CheckRequest(
    val uuid: String
)
