package com.example.first_app.composables.screens.Settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.first_app.Routes
import com.example.first_app.SettingsRoute
import com.example.first_app.composables.libs.Avatar
import com.example.first_app.composables.libs.CustomButton


@Composable

fun SettingsScreen(navController: NavController, parentNavigation: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

    ){

        Avatar(modifier = Modifier
            .size(100.dp)
            .border(5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(50.dp)))
       CustomButton(
           onClick = { navController.navigate(SettingsRoute.profile)},
           text = "Profile",
           buttonModifier = Modifier
               .fillMaxWidth(0.9f)
               .background(Color.Transparent),
       )

    }
}