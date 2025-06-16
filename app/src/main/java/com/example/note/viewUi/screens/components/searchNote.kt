package com.example.note.viewUi.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.note.R

@Composable
fun SearchNote(
    searchValue: String,
    onClearClick: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = searchValue,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "search") },
            placeholder = { Text(text = stringResource(R.string.textPlaceholder)) },
            shape = RoundedCornerShape(20.dp),
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "") },
            trailingIcon = {if (searchValue.isNotBlank()){
                IconButton(onClick = onClearClick) { Icon(Icons.Filled.Clear, contentDescription = "") }
            }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),

            )

    }
}