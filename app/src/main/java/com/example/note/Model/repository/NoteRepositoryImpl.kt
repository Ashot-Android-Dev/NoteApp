package com.example.note.Model.repository

import com.example.note.Model.localData.Note
import com.example.note.Model.localData.NoteDao
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun addNotes(  note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNotes(note: Note) {
        noteDao.deleteNotes(note)
    }

    override suspend fun getAllNote():List<Note> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNotById(id: Int)=noteDao.getNoteById(id)


    override suspend fun updateNote( note: Note) = noteDao.updateNote(note)

    override suspend fun searchNotes(query: String): List<Note> {
        return noteDao.searchNotes(query)
    }
}
