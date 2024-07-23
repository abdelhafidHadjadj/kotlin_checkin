package com.example.first_app.composables.screens.Settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {

        Avatar(modifier = Modifier
            .size(100.dp)
            .border(5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(50.dp)))
       CustomButton(
           onClick = { navController.navigate(SettingsRoute.profile)},
           text = "Personal information",
           buttonModifier = Modifier
               .fillMaxWidth(0.9f)
               .padding(top = 20.dp)
               .background(Color.Transparent),
       )
        HorizontalDivider()
        CustomButton(
            onClick = { navController.navigate(SettingsRoute.profile)},
            text = "Application settings",
            buttonModifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 20.dp)
                .background(Color.Transparent),
        )
        HorizontalDivider()
        CustomButton(
            onClick = { navController.navigate(SettingsRoute.app)},
            text = "Assistance & support",
            buttonModifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 20.dp)
                .background(Color.Transparent),
        )
        HorizontalDivider()
        }
        CustomButton(
            onClick = { navController.navigate(SettingsRoute.app)},
            text = "Logout",
            buttonModifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Red)
            ,
            textModifier = Modifier,
            textColor = Color.White
        )
    }
}