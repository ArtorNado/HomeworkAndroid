package com.example.myapplication.notes.dataBase.dao

import androidx.room.*
import com.example.myapplication.notes.dataBase.converters.DataConverter
import com.example.myapplication.notes.dataBase.entity.Notes
import java.util.*

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

    @Query("UPDATE notes SET title = :newTitle, description = :newDescription, latitude = :newLatitude, longitude = :newLongitude, time = :newTime WHERE id = :id")
    suspend fun updateNote(id: Int, newTitle: String, newDescription: String, newLatitude: String, newLongitude: String, @TypeConverters(DataConverter::class) newTime: Date)
}
