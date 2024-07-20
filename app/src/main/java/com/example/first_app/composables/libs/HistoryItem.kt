package com.example.first_app.composables.libs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable

fun HistoryItem(username: String, tag: String, checkInTime: String) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
            .padding(vertical = 15.dp, horizontal = 20.dp)

            ,Arrangement.spacedBy(10.dp)

    ){
        Text(text = username,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
            )
        HorizontalDivider(modifier = Modifier.border(1.dp, Color.LightGray))
        Row {
            Column {
                Text(text = "Tag")
                Text(text = tag)
            }
            Column {
                Text(text = "Check in time")
                Text(text = checkInTime)
            }
        }
    }
}