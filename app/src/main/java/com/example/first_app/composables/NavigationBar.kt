package com.example.first_app.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.first_app.Routes


data class NavigationItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val bottomNavigationItems = listOf(
    NavigationItem(Routes.homeScreen, "Home", Icons.Filled.Home),
    NavigationItem(Routes.scannerScreen, "Scanner", Icons.Filled.Add),
    NavigationItem(Routes.settingsScreen, "Settings", Icons.Filled.Settings),
    )

val bottomBarRoutes = setOf(Routes.homeScreen, Routes.scannerScreen, Routes.settingsScreen)


val topBarRoutes = setOf(Routes.homeScreen, Routes.settingsScreen)