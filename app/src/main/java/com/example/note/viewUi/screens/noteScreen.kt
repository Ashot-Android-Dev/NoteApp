package com.example.note.viewUi.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.Model.localData.Note
import com.example.note.R
import com.example.note.ui.theme.LightGreen
import com.example.note.viewModel.NoteViewModel
import com.example.note.viewUi.screens.components.AlertDialogExample
import com.example.note.viewUi.screens.components.CardNotes
import com.example.note.viewUi.screens.components.SearchNote
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import com.example.note.viewUi.navigation.AddScreen

@SuppressLint("FrequentlyChangingValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    titleTextTopApp: String,
    color: Color,
    onClick: () -> Unit,
    navController: NavController,
    noteViewModel: NoteViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()

    val allNotes by noteViewModel.allNotes.collectAsState()
    val searchQuery by noteViewModel.searchQuery.collectAsState()
    val searchResults by noteViewModel.searchNotes.collectAsState()

    val notesToShow = if (searchQuery.isBlank()) allNotes else searchResults
    var noteToDelete by remember { mutableStateOf<Note?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    var openDialog by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()
    var showButton by remember { mutableStateOf(true) }

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (lazyListState.isScrollInProgress) {
            showButton = false
        } else {
            delay(1000)
            showButton = true
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier
            .systemBarsPadding()
            .imePadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = titleTextTopApp,
                        color = color
                    )
                },
                colors = topAppBarColors(
                    containerColor = LightGreen,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "home"
                        )
                    }
                },

                )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    onClick = onClick,
                    containerColor = LightGreen,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "add")
                }
            }
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .imePadding()
        ) {
            SearchNote(
                searchValue = searchQuery,
                onValueChange = { newQuery ->
                    noteViewModel.updateSearchQuery(newQuery)
                },
                onClearClick = { noteViewModel.updateSearchQuery("") },
            )
            Spacer(modifier = Modifier.height(7.dp))
            LazyColumn(
                modifier = Modifier
                    .padding(5.dp)
                    .imePadding(),
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            )
            {
                items(notesToShow, key = { note -> note.id }) { note ->
                    CardNotes(
                        textTitleShow = note.title,
                        textNoteShow = note.content,
                        time = note.data,
                        onDelete = {
                            noteToDelete = note
                            openDialog = true
                        },
                        onClick = {
                            navController.navigate(AddScreen(id = note.id))
                        }
                    )
                }

            }
        }
    }
    if (openDialog) {
        AlertDialogExample(
            dialogText = stringResource(R.string.dialogText),
            dialogTitle = stringResource(R.string.dialogTitle),
            onConfirmation = {
                noteToDelete?.let {
                    noteViewModel.deleteNotes(it)
                }
                openDialog = false
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Deleted your note",
                        duration = SnackbarDuration.Short
                    )
                }
            },
            onDismissRequest = {
                openDialog = false
                noteToDelete = null
            })
    }
}


