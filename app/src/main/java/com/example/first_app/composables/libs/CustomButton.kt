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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable


fun CustomButton(
    onClick: () -> Unit,
    text: String,
    buttonModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary
) {
    Button(onClick = onClick,
        modifier = buttonModifier,
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, // Transparent background
            contentColor = MaterialTheme.colorScheme.primary // Set text color
        ),
    ) {
        Text(
            text = text,
            modifier =  textModifier,
            color = textColor
            )
    }
}
