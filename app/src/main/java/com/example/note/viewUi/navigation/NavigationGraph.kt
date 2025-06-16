package com.example.note.viewUi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.note.R
import com.example.note.ui.theme.LightGreen
import com.example.note.viewUi.screens.AddScreen
import com.example.note.viewUi.screens.MainScreen
import com.example.note.viewUi.screens.NotesScreen

@Composable
    fun NavigationGrav(
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = MainScreen,
        ) {
            composable<MainScreen> {
                MainScreen(
                    textButton = stringResource(R.string.buttonText),
                    title = stringResource(R.string.title),
                    colorCont = LightGreen,
                    navController = navController,
                    onclick = {
                        navController.navigate(NotesScreen) {
                        }
                    },
                )
            }
            composable<NotesScreen> {
                NotesScreen(
                    titleTextTopApp = stringResource(R.string.tittleTextTopApp),
                    color = Color.Black,
                    navController = navController,
                    onClick = {
                        navController.navigate(AddScreen()) {
                        }
                    }
                )
            }
            composable<AddScreen> { backStackEntry ->
                val addScreen: AddScreen = backStackEntry.toRoute()
                AddScreen(
                    noteId = addScreen.id,
                    onClick = { navController.navigate(NotesScreen){ popUpTo(navController.graph.findStartDestination().id){inclusive=false}
                        launchSingleTop=true}},
                    onClickBack = {
                        navController.popBackStack()
                    })
            }
        }
    }