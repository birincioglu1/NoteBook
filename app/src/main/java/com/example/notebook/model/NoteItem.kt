package com.example.notebook.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteItem (

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "photo")
    var photo: String?,

    @ColumnInfo(name = "created_date")
    val createdDate: String?,

    @ColumnInfo(name = "update_date")
    var updateDate: String?,

    @ColumnInfo(name = "updated")
    var updated:Boolean=false

) {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var uuid:Int=0
    


}
