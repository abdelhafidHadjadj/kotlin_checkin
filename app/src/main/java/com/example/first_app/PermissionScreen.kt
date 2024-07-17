package com.example.first_app

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable

fun PermissionScreen(navController: NavController) {
    Column {
        Text(text = "Permission Screen")
        Button(onClick = { navController.navigate("login") }) {
            Text(text = "Permission")
        }
    }
}