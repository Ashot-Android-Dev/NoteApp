package com.example.note.Model.localData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1,)
abstract class NoteDataBase : RoomDatabase(){
    abstract fun note():NoteDao
}