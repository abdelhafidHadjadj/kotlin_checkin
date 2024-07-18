package com.example.first_app.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.first_app.Routes


@Composable

fun HomeScreen(navController: NavController){
    Column{
        Text(text= "Home")
        Button(onClick = { navController.navigate(Routes.settingsScreen +"/hafid")}){
            Text(text = "Go to settings")
        }
        Button(onClick = { navController.navigate(Routes.scannerScreen)}) {
            Text(text = "QR Code Scanner")
        }
    }
}