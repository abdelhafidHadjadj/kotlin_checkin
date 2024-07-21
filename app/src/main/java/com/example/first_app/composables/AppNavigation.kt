package com.example.first_app.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.first_app.QrTypes
import com.example.first_app.composables.screens.HomeScreen
import com.example.first_app.composables.screens.LoginScreen
import com.example.first_app.composables.screens.PermissionScreen
import com.example.first_app.Routes
import com.example.first_app.composables.screens.ScannerScreen
import com.example.first_app.composables.screens.SettingsScreen


@Composable

fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val paddingVertical = if (currentRoute in bottomBarRoutes) {
        65.dp
    } else {
        10.dp
    }
    NavHost(navController = navController,
        startDestination = Routes.permissionScreen,
        modifier = Modifier.padding(
            vertical =  paddingVertical,
            horizontal = 15.dp),
        builder = {
        composable(Routes.permissionScreen) {
            PermissionScreen(navController)
        }

        composable(Routes.loginScreen+"?qrBody={qrBody}") {
            val qrBody = it.arguments?.getString("qrBody")
            LoginScreen(navController, qrBody?: "")
        }
        composable(Routes.homeScreen+"?qrBody={qrBody}") {
            val qrBody = it.arguments?.getString("qrBody")

            HomeScreen(navController, qrBody?: "")

        }
        composable(Routes.settingsScreen) {

            SettingsScreen()
        }

        /*composable(Routes.scannerScreen) {
            ScannerScreen(navController, QrTypes.AUTH)
        }*/
            composable(Routes.scannerScreen + "/{type}") {
                val typeString = it.arguments?.getString("type")
                val type = if (typeString != null) QrTypes.valueOf(typeString) else QrTypes.CHECK
                ScannerScreen(navController, type)
            }
    })
}


/*
*
*    composable(Routes.settingsScreen +"/{name}") {
            val name = it.arguments?.getString("name")
            SettingsScreen(name?: "no name")
        }
*
* */