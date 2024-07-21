package com.example.first_app.remote.api.check

import com.example.first_app.remote.api.HttpRoutes
import com.example.first_app.remote.dto.check.CheckRequest
import com.example.first_app.remote.dto.check.CheckResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CheckServiceImp (
    private val client: HttpClient
): CheckService {
    override suspend fun getCheck(checkRequest: CheckRequest): CheckResponse? {
        return try {
            client.post {
                url(HttpRoutes.CHECK)
                contentType(ContentType.Application.Json)
                setBody(checkRequest)
            }.body()
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}") // 3xx - responses
            null
        } catch (e: ClientRequestException) { // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}
