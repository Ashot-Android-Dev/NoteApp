package com.example.note.viewUi.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note.R
import com.example.note.ui.theme.LightGreen
import com.example.note.viewModel.NoteViewModel
import com.example.note.viewUi.screens.Constants.CONTENT_TEXT
import com.example.note.viewUi.screens.components.TitleInput
import com.example.note.viewUi.screens.components.TransparentHintTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onClick: () -> Unit,
    onClickBack: () -> Unit,
    noteId: Int? = null,
    noteViewModel: NoteViewModel = hiltViewModel(),
) {
    val notes by noteViewModel.allNotes.collectAsState()

    LaunchedEffect(noteId) {
        if (noteId==null){
            noteViewModel.clearFields()
        } else{
            noteViewModel.loadNotById(noteId)
        }   }
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightGreen
                ),
                title = {
                    TitleInput(
                        text = noteViewModel.title, onTextChange = { noteViewModel.title = it },
                        placeholder = "title"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .imePadding(),
                onClick = {
                    if (noteId != null) {
                        noteViewModel.updateNote(noteId)
                    } else noteViewModel.saveNotes()
                    onClick()
                },
                containerColor = LightGreen,
                contentColor = Color.White
            ) {
                Icon(painter = painterResource(R.drawable.save), contentDescription = "save")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 6.dp)
                .imePadding(),

            ) {
            item {
                TransparentHintTextField(
                    modifier = Modifier.fillParentMaxSize(),
                    text = noteViewModel.content,
                    onValueChange = { noteViewModel.content = it },
                    hint = if (noteViewModel.content.isEmpty()) CONTENT_TEXT else "",
                )
            }
        }
    }
}



