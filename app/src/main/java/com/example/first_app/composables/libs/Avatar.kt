package com.example.first_app.composables.libs

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.first_app.R

@Composable

fun Avatar(imgUrl: String = "default", modifier: Modifier) {
    Image(
        painter = if (imgUrl == "default") painterResource(id = R.drawable.avatar) else painterResource(id = R.drawable.avatar),
        contentDescription = if (imgUrl == "default") stringResource(id = R.string.avatar_content_description) else stringResource(id = R.string.avatar_content_description),
        modifier = modifier
        )

}