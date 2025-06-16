package com.example.note.Model.localData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    suspend fun getAllNotes():List<Note>

    @Update
    suspend fun updateNote(noteEntity: Note)

    @Delete
    suspend fun deleteNotes(noteEntity: Note)

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    suspend fun getNoteById(id: Int):Note?

    @Query("DELETE FROM notes WHERE id=:id")
    suspend fun deleteById(id: Int)

    @Query("""
    SELECT * FROM notes 
    WHERE title LIKE '%' || :searchQuery || '%' 
    OR content LIKE '%' || :searchQuery || '%'
    ORDER BY data DESC
""")
    suspend fun searchNotes(searchQuery: String): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: Note)
}