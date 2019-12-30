package com.example.myapplication.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.notes_list_template.view.*

class NotesViewHolder(
        override val containerView: View,
        private val clickLambda: (NotesData) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val tv = containerView.tv
    private val tv_description = containerView.tv_description
    private var cv = containerView.card_view

    fun bind(notes: NotesData) {
        /*cv.minimumHeight = (100..500).random()
        cv.minimumWidth = (1..100).random()*/
        cv.setCardBackgroundColor((0..25).random())
        var s = (1..35).random().toFloat()
        tv.textSize = s
        tv.text = notes.title
        tv_description.text = notes.description
        tv_description.textSize = s
        itemView.setOnClickListener {
            clickLambda(notes)
        }
    }

    fun update(bundle: Bundle) {
        tv.text = bundle.getString("title") ?: "NULL"
        tv_description.text = bundle.getString("description") ?: "NULL"
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (NotesData) -> Unit) =
                NotesViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.notes_list_template, parent, false),
                        clickLambda
                )
    }
}
