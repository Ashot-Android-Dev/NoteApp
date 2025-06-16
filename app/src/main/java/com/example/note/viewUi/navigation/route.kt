package com.example.note.viewUi.navigation

import kotlinx.serialization.Serializable

@Serializable
data object  MainScreen
@Serializable
data object NotesScreen
@Serializable
data class AddScreen(val id:Int?=null)

