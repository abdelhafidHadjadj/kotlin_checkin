package com.example.first_app.composables.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.first_app.Routes
import com.example.first_app.composables.libs.HistoryItem


@Composable

fun HomeScreen(navController: NavController){

    Column (
    ){

        HistoryItem(username = "Hafid", tag = "Participant", checkInTime = "2024-02-01 20:25:35")
    }
}