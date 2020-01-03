package com.example.myapplication.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.notes.dataBase.AppDatabase
import com.example.myapplication.notes.info.NotesInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class MainActivity : AppCompatActivity(), OnFragmentListener, CoroutineScope by MainScope() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase(this)
        setMainFragment()
    }

    private fun setMainFragment() {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.container, MainFragment.newInstance())
                addToBackStack(MainFragment::class.java.name)
                commit()
            }
        }
    }

    private fun setNotesInfoFragment(action: String, id: Int, title: String, description: String) {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.container, NotesInfo.newInstance(action, id, title, description))
                commit()
            }
        }
    }

    override fun changeFragment(comand: String) {
        when (comand) {
            "add" -> {
                setNotesInfoFragment("add", 0, "", "")
            }
            "end_add" ->
                supportFragmentManager.also {
                    it.beginTransaction().apply {
                        replace(R.id.container, MainFragment.newInstance())
                        commit()
                    }
                }
        }
    }

    override fun changeNotes(action: String, id: Int, title: String, description: String) {
        setNotesInfoFragment(action, id, title, description)
    }


}
