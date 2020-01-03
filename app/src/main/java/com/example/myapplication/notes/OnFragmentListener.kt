package com.example.myapplication.notes

interface OnFragmentListener {
    fun changeFragment(comand: String)
    fun changeNotes(action: String, id: Int, title: String, description: String)
}
