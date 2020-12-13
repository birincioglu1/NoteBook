package com.example.notebook.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notebook.model.NoteItem

@Database(entities = arrayOf(NoteItem::class), version = 1,exportSchema = false)
abstract class NoteDatabase :RoomDatabase() {

        abstract fun noteDao():NoteDao

        companion object{

                @Volatile
                private var instace:NoteDatabase?=null

                private val lock=Any()

                operator fun invoke(context: Context)= instace ?: synchronized(lock){
                        instace?: createDatabase(context).also {
                                instace=it
                        }
                }
                private fun createDatabase(context: Context)=Room.databaseBuilder(
                        context.applicationContext,NoteDatabase::class.java,"note_database"
                ).build()







        }

}