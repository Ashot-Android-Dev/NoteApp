package com.example.note.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.Model.localData.Note
import com.example.note.Model.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    private val _allNotes = MutableStateFlow<List<Note>>(emptyList())
    val allNotes: StateFlow<List<Note>> = _allNotes.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    var searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults  = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes: StateFlow<List<Note>> = _searchResults .asStateFlow()

    private var currentSearchJob: Job?=null

    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var data by mutableStateOf("")

    init {
        loadNotes()
        observeSearch()
    }
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
    @OptIn(FlowPreview::class)
    private fun observeSearch() {
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isBlank()) {
                        _searchResults .value = emptyList()
                    } else {
                        try {
                            _searchResults .value = noteRepository.searchNotes(query)
                        } catch (e: Exception) {
                            _searchResults .value = emptyList()
                        }
                    }
                }
        }
    }
    fun loadNotById(id:Int){
        viewModelScope.launch {
            val note =noteRepository.getNotById(id)
            note?.let {
                title=it.title
                content=it.content
            }
        }
    }
    fun updateNote(id: Int){
        viewModelScope.launch {
            val update=Note(id=id,title=title,content=content,
                data = getCurrentData()
            )
            noteRepository.updateNote(update)
            loadNotes()
            clearFields()
        }
    }
    private fun loadNotes() {
        viewModelScope.launch {
            _allNotes.value = noteRepository.getAllNote()
        }
    }

    fun saveNotes() {
        if (title.isBlank() || content.isBlank()){
            return
        }
        viewModelScope.launch {
            val note=Note(title = title, content = content, data = getCurrentData())
            noteRepository.addNotes(note)
            loadNotes()
            clearFields()
        }
    }

    fun deleteNotes(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNotes(note)
            _allNotes.value = noteRepository.getAllNote()
        }
    }
    private fun getCurrentData():String{
        return SimpleDateFormat("<<dd.MM.yyyy/ HH:mm>>", Locale.getDefault()).format(Date())
    }
    fun clearFields(){
        title=""
        content=""
        data=""
    }


}