package com.example.first_app.composables.screens


import android.Manifest
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

@Composable

fun LoginScreen(navContoller: NavController, qrBody: String = ""){
    /**
     * if qr body => send http req if success > set token + redirect home
     *                             else err > msg "Login Failed"
     */
    val type = QrTypes.AUTH
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