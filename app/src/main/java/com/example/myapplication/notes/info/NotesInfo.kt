package com.example.myapplication.notes.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.notes.MainActivity
import com.example.myapplication.notes.OnFragmentListener
import com.example.myapplication.notes.dataBase.AppDatabase
import com.example.myapplication.notes.dataBase.entity.Notes
import kotlinx.android.synthetic.main.info_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NotesInfo : Fragment(), OnFragmentListener, CoroutineScope by MainScope() {

    private lateinit var mListener: OnFragmentListener
    private lateinit var db: AppDatabase


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase(activity as MainActivity)
        et_title.setText(arguments?.getString("title").toString())
        et_description.setText(arguments?.getString("description").toString())
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
            if (arguments?.getString("action").toString().equals("add")) {
                addNotes(et_title.text.toString(), et_description.text.toString())
                mListener.changeFragment("end_add")
            } else {
                updateNotes(arguments?.getInt("id") ?: 0, et_title.text.toString(),
                        et_description.text.toString())
                mListener.changeFragment("end_add")
            }
        }
    }

    private fun updateNotes(id: Int, title: String, description: String) {
        launch { db.notesDao().updateNote(id, title, description) }
    }

    private fun addNotes(title: String, description: String) {
        launch { db.notesDao().save(Notes(0, title, description)) }
    }

    override fun changeFragment(comand: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeNotes(action: String, id: Int, title: String, description: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        private const val ARG_ACTION = "action"
        private const val ARG_ID = "id"
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(
                action: String = "",
                id: Int = 0,
                title: String = "",
                description: String = ""
        ): NotesInfo = NotesInfo().apply {
            arguments = Bundle().apply {
                putString(ARG_ACTION, action)
                putInt(ARG_ID, id)
                putString(ARG_TITLE, title)
                putString(ARG_DESCRIPTION, description)
            }
        }
    }
}
