package com.example.myapplication.notes.dataBase.dao

import androidx.room.*
import com.example.myapplication.notes.dataBase.entity.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(notes: Notes)

    @Query("SELECT * FROM notes")
    suspend fun getNotes(): List<Notes>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Int): Notes?

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Query("UPDATE notes SET title = :newTitle, description = :newDescription WHERE id = :id")
    suspend fun updateNote(id: Int, newTitle: String, newDescription: String)
}
