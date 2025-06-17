package com.example.note.Di.repositoryModule


import com.example.note.Model.repository.NoteRepository
import com.example.note.Model.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindNoteRepository(repository: NoteRepositoryImpl): NoteRepository
}

