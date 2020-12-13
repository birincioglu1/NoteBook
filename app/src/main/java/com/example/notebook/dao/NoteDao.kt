package com.example.notebook.dao

import androidx.room.*
import com.example.notebook.model.NoteItem

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table order by uuid DESC")
    suspend fun getAll(): List<NoteItem>

    @Insert
    suspend fun addNote(note: NoteItem)

    @Query("SELECT * FROM NOTE_TABLE WHERE uuid= :noteId")
    suspend fun getNoteById(noteId:Int):NoteItem


    @Query("SELECT * FROM note_table WHERE title LIKE :char ")
    suspend fun searchNode(char:String): List<NoteItem>

    @Query("DELETE FROM note_table WHERE uuid= :noteId")
    suspend fun deleteById(noteId:Int)

    @Update
    suspend fun updateNode(note: NoteItem)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()
}