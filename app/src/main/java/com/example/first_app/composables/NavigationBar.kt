package com.example.first_app.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.first_app.QrTypes
import com.example.first_app.Routes


data class NavigationItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val type = QrTypes.CHECK
val bottomNavigationItems = listOf(
    NavigationItem(Routes.homeScreen+"?qrBody={qrBody}", "History", Icons.Filled.History),
    NavigationItem(Routes.scannerScreen+"/${type.name}", "Scanner", Icons.Filled.QrCodeScanner),
    NavigationItem(Routes.settingsScreen, "Settings", Icons.Filled.Menu),
    )

val bottomBarRoutes = setOf(Routes.homeScreen+"?qrBody={qrBody}", Routes.settingsScreen)


val topBarRoutes = setOf(Routes.homeScreen, Routes.settingsScreen)