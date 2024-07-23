package com.example.first_app.composables.screens.Settings


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.first_app.composables.libs.CustomTextField


@Composable

fun ProfileScreen(navController: NavController, parentNavigation: NavHostController){
    Column {
        CustomTextField(value = "Hadjadj", onChange = {}, submit = {  }, label = "Firstname", placeholder = "")
    }
}