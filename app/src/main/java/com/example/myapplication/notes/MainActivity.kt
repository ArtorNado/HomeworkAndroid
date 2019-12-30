package com.example.myapplication.notes

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAdapter: NotesAdapter? = null
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private val notesList: ArrayList<NotesData> = NotesList.getNotesList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = NotesAdapter(NotesList.getNotesList()) { NotesData ->
            Log.e("TOUCH", "TOUCH")
        }
        mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_notes_list.layoutManager = mLayoutManager
        setRecyclerViewItemTouchListener()
        rv_notes_list.adapter = mAdapter
    }

    private fun deleteItem(notes: NotesData) {
        notesList.remove(notes)
        mAdapter?.updateList(notesList)
    }

    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                notesList.removeAt(position)
                mAdapter?.updateList(notesList)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        rv_notes_list.addItemDecoration(itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(rv_notes_list)
    }

}
