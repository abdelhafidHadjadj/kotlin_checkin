package com.example.first_app

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable

fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(navController = navController,
        startDestination = Routes.permissionScreen,
        modifier = Modifier.padding(60.dp),
        builder = {
        composable(Routes.permissionScreen) {
            PermissionScreen(navController)
        }
        composable(Routes.loginScreen) {
            LoginScreen(navController)
        }
        composable(Routes.homeScreen) {
            HomeScreen(navController)
        }
        composable(Routes.settingsScreen+"/{name}") {
            val name = it.arguments?.getString("name")
            SettingsScreen(name?: "no name")
        }
        composable(Routes.scannerScreen) {
            ScannerScreen()
        }
    })
}