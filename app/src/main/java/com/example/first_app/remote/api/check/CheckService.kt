package com.example.first_app.remote.api.check

import com.example.first_app.remote.dto.check.CheckRequest
import com.example.first_app.remote.dto.check.CheckResponse

interface CheckService {
    suspend fun getCheck(checkRequest: CheckRequest): CheckResponse?
}