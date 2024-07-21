package com.example.first_app.remote.api.auth

import com.example.first_app.remote.dto.auth.AuthRequest
import com.example.first_app.remote.dto.auth.AuthResponse

interface AuthService {
    suspend fun login(authRequest: AuthRequest): AuthResponse?
}