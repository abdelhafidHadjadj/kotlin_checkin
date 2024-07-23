package com.example.first_app

object Routes {
        var loginScreen = "login"
        var homeScreen = "home"
        var settingsScreen = "settings"
        var permissionScreen = "permission"
        var scannerScreen = "scanner/{type}"
}

object  SettingsRoute {
        var main = Routes.settingsScreen
        var profile = "profile"
        var app = "app"
}