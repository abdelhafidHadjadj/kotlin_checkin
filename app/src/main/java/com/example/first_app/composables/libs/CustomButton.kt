package com.example.first_app.composables.libs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable


fun CustomButton(onClick: () -> Unit, text: String) {
    Button(onClick = {
        // Trigger the permission request when button is clicked
    },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
        ,colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Transparent background
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp) // Remove elevation


    ) {
        Text(text = "Request Permission")
    }
}
