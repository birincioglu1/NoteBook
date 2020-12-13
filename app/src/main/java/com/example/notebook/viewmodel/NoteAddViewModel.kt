package com.example.notebook.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.notebook.dao.NoteDatabase
import com.example.notebook.model.NoteItem
import kotlinx.coroutines.launch

class NoteAddViewModel(application: Application):BaseViewModel(application) {

    var titleText=MutableLiveData<String>()


     fun addNoteSQLite(noteItem:NoteItem)
    {
        launch {
            val dao=NoteDatabase(getApplication()).noteDao()
            dao.addNote(noteItem)

        }
    }




}