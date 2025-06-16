package com.example.note.Di.NoteModule

import android.content.Context
import androidx.room.Room
import com.example.note.Model.localData.NoteDao
import com.example.note.Model.localData.NoteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {
    @Provides
    @Singleton
    fun provideNote(@ApplicationContext context: Context):NoteDataBase{
        return Room.databaseBuilder(
            context,
            NoteDataBase::class.java,
            name = "notes",
        ).build()
    }
    @Provides
    @Singleton
    fun provideNoteDao(dataBase: NoteDataBase): NoteDao {
        return dataBase.note()
    }
}