package com.example.notebook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notebook.adapter.NoteClickListener
import com.example.notebook.dao.NoteDatabase
import com.example.notebook.model.NoteItem
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):BaseViewModel(application) {

    var notes=MutableLiveData<List<NoteItem>>()
    var searchNotes=MutableLiveData<List<NoteItem>>()
    fun getDataFromSQLite()
    {
        launch {
            val dao= NoteDatabase(getApplication()).noteDao()
            notes.value=dao.getAll()
        }


    }
    fun searchNode(char:String)
    {
        launch {
            val dao= NoteDatabase(getApplication()).noteDao()
            searchNotes.value=dao.searchNode(char)
        }


    }




}