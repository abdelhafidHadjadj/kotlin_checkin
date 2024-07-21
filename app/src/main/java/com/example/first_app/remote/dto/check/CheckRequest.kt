package com.example.first_app.remote.dto.check

import kotlinx.serialization.Serializable
import java.util.UUID



@Serializable
data class CheckRequest(
    val uuid: UUID
)
