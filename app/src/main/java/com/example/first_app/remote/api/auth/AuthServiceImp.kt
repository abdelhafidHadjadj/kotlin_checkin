package com.example.first_app.remote.api.auth

import android.util.Log
import com.example.first_app.remote.api.HttpRoutes
import com.example.first_app.remote.dto.auth.AuthRequest
import com.example.first_app.remote.dto.auth.AuthResponse
import com.example.first_app.remote.dto.auth.AuthResponseWithHeaders
import com.example.first_app.remote.dto.auth.TestResponse
import io.ktor.client.*
import io.ktor.client.call.receive
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.util.toMap
import java.util.regex.Pattern


class AuthServiceImp(
    private val client: HttpClient
) : AuthService {
    override suspend fun login(authRequest: AuthRequest): AuthResponseWithHeaders? {
        return try {
            val response: HttpResponse = client.get {
                url(HttpRoutes.LOGIN)
                parameter("uuid", authRequest.uuid)
            }
            val authResponse = response.receive<AuthResponse>()
            val headers = response.headers
            val singleValueHeaders = headers.toMap().mapValues { it.value.joinToString(", ") }
            val setCookie = headers["set-cookie"]
            val sessionId = extractSessionId(setCookie)
            val status = response.status.value
            AuthResponseWithHeaders(authResponse, singleValueHeaders, sessionId, status)
        } catch (e: RedirectResponseException) {
            Log.d("AuthServiceImp", "Redirect Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            Log.d("AuthServiceImp", "Client Request Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            Log.d("AuthServiceImp", "Server Response Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            Log.d("AuthServiceImp", "Error: ${e.message}")
            null
        }
    }

    private fun extractSessionId(setCookie: String?): String? {
        val pattern = Pattern.compile("session_id=([^;]+)")
        val matcher = pattern.matcher(setCookie ?: "")
        return if (matcher.find()) {
            matcher.group(1)
        } else {
            null
        }
    }
}

class TestImp(
    private  val client: HttpClient
): TestService {
    override suspend fun test(uuid: String): TestResponse? {
        return try {

            val res = client.get<TestResponse>{
                url(HttpRoutes.TEST+"?uuid=$uuid")
                /*contentType(ContentType.Application.Json)
                setBody(authRequest)*/
            }
            Log.d("res imp", "$res")
            res
        }  catch (e: RedirectResponseException) {
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