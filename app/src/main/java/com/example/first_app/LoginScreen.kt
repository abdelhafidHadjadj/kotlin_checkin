package com.example.first_app


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable

fun LoginScreen(navContoller: NavController){
    Column {
        Text(text = "Login")
        Button(onClick = { navContoller.navigate("home")}) {
            Text(text = "Login")
        }

    }
}