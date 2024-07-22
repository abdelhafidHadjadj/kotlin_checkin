package com.example.first_app.composables.screens


import android.Manifest
import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.first_app.utils.saveSessionId
import com.example.first_app.utils.verifySessionId
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.launch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@Composable

fun LoginScreen(navContoller: NavController, qrBody: String = ""){
    /**
     * if qr body => send http req if success > set token + redirect home
     *                             else err > msg "Login Failed"
     */
    val type = QrTypes.AUTH
    val authService = remember { AuthService.create() }
    val authRes = remember { mutableStateOf("") }
    var errorMessage = remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val isLoggedIn = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()



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
        if (qrBody.isNotBlank() && QrTypes.AUTH.name == "AUTH") {
            val (responseBody, error) = performLogin(authService, qrBody, context)
            Log.d("res body", "$responseBody")
            if (error == null) {
                authRes.value = responseBody ?: ""
                isLoggedIn.value = true
                coroutineScope.launch {
                    delay(1500)  // Show "Verified" for 2 seconds
                    navContoller.navigate(Routes.homeScreen+"?qrBody=$qrBody") {
                        popUpTo(Routes.loginScreen) { inclusive = true }  // Optionally clear the back stack
                    }
                }

            } else {
                errorMessage.value = error
            }

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

            AnimationComponent(if (isLoggedIn.value) "authed.json" else "scan_card.json")

        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = if (isLoggedIn.value) "Verified" else "Sign in",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            if(!isLoggedIn.value) {
            Text(
                text = "Scan the QR code on your badge to identify",
                textAlign = TextAlign.Center
            )

            Button(onClick = {
                navContoller.navigate(Routes.scannerScreen+"/${type.name}")
                /*navContoller.navigate(Routes.homeScreen+"?qrBody=$qrBody")*/
            } ,
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
}




// Define the suspend function for performing login
suspend fun performLogin(authService: AuthService, uuid: String, context: Context): Pair<String?, String?> {
    Log.d("uuid insid fun", "$uuid")
    return withContext(Dispatchers.IO) {
        try {
            val authRequest = AuthRequest(uuid)
            val response = authService.login(authRequest)
            Log.d("inside perform log", "Response: ${response?.response?.success}") // Log the entire response object
            // Handle the nullable response
            if (response != null && response.response.success) {
                val sessionId = response.response.body
                saveSessionId(context, sessionId)
                verifySessionId(context)
                Pair(response.response.body, null)  // Success case

            } else {
                Pair(null, "Request failed")  // Failure case
            }
        } catch (e: Exception) {
            Pair(null, "Error: ${e.message}")  // Error case
        }
    }
}
