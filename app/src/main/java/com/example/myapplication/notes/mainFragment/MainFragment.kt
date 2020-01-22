package com.example.myapplication.notes.mainFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.notes.MainActivity
import com.example.myapplication.notes.OnFragmentListener
import com.example.myapplication.notes.constants.Constants
import com.example.myapplication.notes.dataBase.AppDatabase
import com.example.myapplication.notes.dataBase.entity.Notes
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainFragment : Fragment(), OnFragmentListener, CoroutineScope by MainScope() {

    private var mAdapter: NotesAdapter? = null
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var db: AppDatabase
    private lateinit var mListener: OnFragmentListener

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        db = AppDatabase(activity as MainActivity)
        launch {
            mAdapter = NotesAdapter(getListNotes()) {
                mListener.changeNotes(Constants.ACTION.UPDATE_ACTION, it)
            }
            mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rv_notes_list.layoutManager = mLayoutManager
            setRecyclerViewItemTouchListener()
            rv_notes_list.adapter = mAdapter
        }
        initListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + "must implement interface")
        }
    }

    private fun initListeners() {
        btn_add.setOnClickListener {
            mListener.changeFragment(Constants.ACTION.ADD_ACTION)
        }
        btn_delete_all.setOnClickListener {
            launch {
                deleteAllNotes()
                mAdapter?.updateList(getListNotes())
            }
        }
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
                launch {
                    val position = viewHolder.adapterPosition
                    deleteNotes(getListNotes()[position])
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        rv_notes_list.addItemDecoration(itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(rv_notes_list)
    }

    private fun deleteNotes(notes: Notes) {
        launch {
            db.notesDao().deleteNotes(notes)
            mAdapter?.updateList(getListNotes())
        }
    }

    private fun deleteAllNotes() {
        launch {
            db.notesDao().deleteAllNotes()
            mAdapter?.updateList(getListNotes())
        }
    }

    private suspend fun getListNotes(): List<Notes> = db.notesDao().getNotes()

    override fun changeFragment(comand: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeNotes(action: String, note: Notes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

}
