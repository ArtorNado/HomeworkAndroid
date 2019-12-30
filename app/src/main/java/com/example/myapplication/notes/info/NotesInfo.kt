package com.example.myapplication.notes.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class NotesInfo : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    companion object {

        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(
                title: String = "NULL",
                description: String = "NULL"
        ): NotesInfo = NotesInfo().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_DESCRIPTION, description)
            }
        }
    }
}
