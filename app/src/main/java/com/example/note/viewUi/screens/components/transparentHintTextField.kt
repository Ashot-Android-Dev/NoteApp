package com.example.note.viewUi.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransparentHintTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
) {
    Box(
        modifier = modifier.padding(
            horizontal = 5.dp
        )
    ) {
        val brush = remember {
            Brush.linearGradient(
                colors = listOf(Color.Red, Color.Green, Color.Magenta)
            )
        }

        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = modifier,
            textStyle = TextStyle(brush, fontSize = 23.sp),
            cursorBrush = SolidColor(Color.Red),

            )
        if (isHintVisible) {
            Text(text = hint)
        }
    }
}