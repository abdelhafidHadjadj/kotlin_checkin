package com.example.first_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.permissionScreen, builder = {
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
    }
}
