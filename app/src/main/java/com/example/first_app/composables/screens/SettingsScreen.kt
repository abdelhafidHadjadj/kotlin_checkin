package com.example.first_app.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable

fun SettingsScreen(name: String){
    Column {
        Text(text = "I'm $name")
    }
}