package com.example.myapplication.notes.dataBase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Notes(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @ColumnInfo(name = "title")
        var title: String?,
        @ColumnInfo(name = "description")
        var description: String?,
        @ColumnInfo(name = "latitude")
        var latitude: String?,
        @ColumnInfo(name = "longitude")
        var longitude: String?,
        @ColumnInfo(name = "time")
        var time: Long?
)
