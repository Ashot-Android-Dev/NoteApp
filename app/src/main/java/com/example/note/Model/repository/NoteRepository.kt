package com.example.note.Model.repository

import com.example.note.Model.localData.Note

interface NoteRepository {
    suspend fun addNotes(note: Note)

    suspend fun deleteNotes( note: Note)

    suspend fun getAllNote(): List<Note>

    suspend fun getNotById(id: Int): Note?

    suspend fun updateNote( note: Note)
    suspend fun searchNotes(query: String): List<Note>

}
