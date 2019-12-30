package com.example.myapplication.notes.diff

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.notes.NotesData

object Diff : DiffUtil.ItemCallback<NotesData>() {

    override fun areItemsTheSame(oldItem: NotesData, newItem: NotesData): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: NotesData, newItem: NotesData): Boolean =
            oldItem.description == newItem.description

    override fun getChangePayload(oldItem: NotesData, newItem: NotesData): Any? {
        val diffBundle = Bundle()
        if (oldItem.title != newItem.title) {
            diffBundle.putString("title", newItem.title)
        }
        if (oldItem.description != newItem.description) {
            diffBundle.putString("description", newItem.description)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }

}
