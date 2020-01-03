package com.example.myapplication.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.notes.dataBase.entity.Notes
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.notes_list_template.view.*

class NotesViewHolder(
        override val containerView: View,
        private val clickLambda: (Notes) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val tv = containerView.tv
    private val tv_description = containerView.tv_description
    private var cv = containerView.card_view

    fun bind(notes: Notes) {
        tv.text = notes.title
        tv_description.text = notes.description
        itemView.setOnClickListener {
            clickLambda(notes)
        }
    }

    fun update(bundle: Bundle) {
        tv.text = bundle.getString("title") ?: "NULL"
        tv_description.text = bundle.getString("description") ?: "NULL"
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Notes) -> Unit) =
                NotesViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.notes_list_template, parent, false),
                        clickLambda
                )
    }
}
