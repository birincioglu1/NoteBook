package com.example.notebook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData

import com.example.notebook.dao.NoteDatabase
import com.example.notebook.model.NoteItem
import kotlinx.coroutines.launch


class NoteDetailViewModel(application: Application):BaseViewModel(application)  {

    val note=MutableLiveData<NoteItem>()

    fun getNoteById(uuıd:Int)
    {
        launch {
            val dao= NoteDatabase(getApplication()).noteDao()
            note.value=dao.getNoteById(uuıd)

        }
    }
    fun updateNote(noteItem: NoteItem)
    {
        launch {
            val dao= NoteDatabase(getApplication()).noteDao()
            dao.updateNode(noteItem)


        }
    }
    fun deleteNote(uuıd: Int)
    {
        launch {
            val dao= NoteDatabase(getApplication()).noteDao()
            dao.deleteById(uuıd)


        }
    }


}