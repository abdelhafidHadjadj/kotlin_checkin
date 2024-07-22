package com.example.first_app.remote.api.check

import com.example.first_app.remote.api.HttpRoutes
import com.example.first_app.remote.dto.check.CheckRequest
import com.example.first_app.remote.dto.check.CheckResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class CheckServiceImp (
    private val client: HttpClient
): CheckService {
    override suspend fun getCheck(checkRequest: CheckRequest): CheckResponse? {
        return try {
            client.post<CheckResponse> {
                url(HttpRoutes.CHECK)
                contentType(ContentType.Application.Json)
                body = checkRequest
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}") // 3xx - responses
            null
        } catch (e: ClientRequestException) { // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) { // 5xx
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}
