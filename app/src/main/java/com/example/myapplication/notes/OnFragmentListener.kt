package com.example.myapplication.notes

import com.example.myapplication.notes.dataBase.entity.Notes

interface OnFragmentListener {
    fun changeFragment(comand: String)
    fun changeNotes(action: String, note: Notes)
}
