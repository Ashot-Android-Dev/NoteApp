package com.example.note.viewUi.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.ui.theme.LightSurface
import com.example.note.ui.theme.MintLight


@Composable
fun CardNotes(
    modifier: Modifier = Modifier,
    textTitleShow: String,
    textNoteShow: String,
    time: String,
    onDelete: () -> Unit,
    onClick: () -> Unit,
) {
    val gradient = listOf(MintLight, MintLight, LightSurface)
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(6.dp),
    ) {
        Column(
            modifier = Modifier
                .background(brush = Brush.verticalGradient(gradient))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = textTitleShow,
                    fontSize = 25.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.weight(1f)
                )
                Text(
                    text = time,
                )
            }
            HorizontalDivider(color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = textNoteShow,
                fontSize = 20.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "delete")
                }
            }
        }
    }
}
