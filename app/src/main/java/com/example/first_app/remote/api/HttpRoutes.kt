package com.example.first_app.remote.api

object HttpRoutes {
    private const val BASE_URL = "https://kotlin.celec.club"
    const val LOGIN = "$BASE_URL/v1/auth"
    const val CHECK = "$BASE_URL/v2/check"
    const val TEST = "https://jsonplaceholder.typicode.com/todos/1"
}