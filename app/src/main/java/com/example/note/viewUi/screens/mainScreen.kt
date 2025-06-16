package com.example.note.viewUi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note.R


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onclick: () -> Unit,
    colorCont: Color? = null,
    textButton: String,
    title: String,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_photo),
            contentDescription = "Logo",
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 24.sp
            )
            HorizontalDivider()
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onclick,
                modifier = Modifier
                    .fillMaxWidth(0.91f)
                    .padding(bottom = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorCont ?: Color.Red
                )
            ) {
                Text(
                    text = textButton,
                    fontSize = 22.sp,
                    letterSpacing = 0.1.sp
                )
            }
        }
    }
}