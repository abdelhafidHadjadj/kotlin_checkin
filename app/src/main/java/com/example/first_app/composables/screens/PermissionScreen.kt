package com.example.first_app.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@Composable

fun PermissionScreen(navController: NavController) {
    Column (
        modifier = Modifier
    ){
        Text(text = "Permission Screen")
        Button(onClick = { navController.navigate("login") }) {
            Text(text = "Permission !!!!!")
        }
    }
}