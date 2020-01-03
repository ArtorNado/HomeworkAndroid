package com.example.myapplication.notes.diff

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.notes.dataBase.entity.Notes

object Diff : DiffUtil.ItemCallback<Notes>() {

    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean =
            oldItem.description == newItem.description

    override fun getChangePayload(oldItem: Notes, newItem: Notes): Any? {
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
