package com.example.note.viewUi.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleInput(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (text.isEmpty()) {
            Text(
                text = placeholder,
                color = Color.Gray
            )
        }

        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
