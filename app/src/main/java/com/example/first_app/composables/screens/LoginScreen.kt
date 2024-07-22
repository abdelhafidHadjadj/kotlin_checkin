package com.example.first_app.composables.screens


import android.Manifest
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.first_app.QrTypes
import com.example.first_app.Routes
import com.example.first_app.composables.libs.AnimationComponent
import com.example.first_app.remote.api.auth.AuthService
import com.example.first_app.remote.api.auth.TestService
import com.example.first_app.remote.dto.auth.AuthRequest
import com.example.first_app.remote.dto.auth.AuthResponse
import com.example.first_app.remote.dto.auth.AuthResponseWithHeaders
import com.example.first_app.remote.dto.auth.TestResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.launch

@Composable

fun LoginScreen(navContoller: NavController, qrBody: String = ""){
    /**
     * if qr body => send http req if success > set token + redirect home
     *                             else err > msg "Login Failed"
     */
    val type = QrTypes.AUTH
    val authService = remember { AuthService.create() }
    val authRes = remember { mutableStateOf<AuthResponseWithHeaders?>(null) }
    var errorMessage = remember { mutableStateOf<String?>(null) }
    val status = remember { mutableStateOf<String?>(null) }

    /*val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        {
        val res: String = client.get("http://api.celec.club/")
        /*val resp: HttpResponse = client.get("https://jsonplaceholder.typicode.com/todos/1")
        Log.d("resp", "$resp")*/
        Log.d("res", "$res")
    }
    }*/
    /*
    LaunchedEffect(Unit) {
        try {
            val response = testService.test(uuid = "ddddd")
            testResponse.value = response
            Log.d("response login", "$response")
            if (response == null) {
                errorMessage.value = "Request failed"
            }
        } catch (e: Exception) {
            errorMessage.value = "Error: ${e.message}"
            Log.d("error", "$e")
        }
    }*/
    LaunchedEffect(Unit) {
        try {
            val uuid = "7be0a90e-4763-11ef-807a-632c7ad3a20e"
            val authRequest = AuthRequest(uuid)
            val response = authService.login(authRequest)

            // Handle the nullable response
            if (response != null) {
                authRes.value = response

                // Extract sessionId safely
                val setCookie = response.headers?.get("set-cookie")
                val sessionId = setCookie
                    ?.split(";")
                    ?.firstOrNull()
                    ?.split("=")
                    ?.getOrNull(1)

                // Safely access status
                status.value = response.status.toString()

                Log.d("response", "$response")
                Log.d("sessionID", "$sessionId")
            } else {
                errorMessage.value = "Request failed"
            }
        } catch (e: Exception) {
            errorMessage.value = "Error: ${e.message}"
            Log.d("error", "$e")
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
        ) {

            AnimationComponent("scan_card.json")

        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "response : $authRes \n error: $errorMessage",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Sign in",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Scan the QR code on your badge to identify",
                textAlign = TextAlign.Center
            )

            Button(onClick = {
                navContoller.navigate(Routes.scannerScreen+"/${type.name}")
                /*navContoller.navigate(Routes.homeScreen+"?qrBody=$qrBody")*/
            },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background,

                    ),
                elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                contentPadding = PaddingValues(10.dp, 20.dp)

            ) {
                Text(text = "SCAN NOW")
            }
        }
    }
}