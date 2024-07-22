package com.example.first_app.composables.libs

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CustomTextField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String
    ) {
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        placeholder = {Text(placeholder)},
        label = { Text(label) },

    )
}



@Preview(showBackground = true)
@Composable
fun PreviewCustomTextField() {
    Column {
        CustomTextField(
            value = "",
            onChange = {},
            submit = {},
            label = "Label",
            placeholder = "Placeholder"
        )
    }
}