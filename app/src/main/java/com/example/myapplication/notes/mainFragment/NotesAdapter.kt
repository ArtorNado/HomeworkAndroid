package com.example.myapplication.notes.mainFragment

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.notes.dataBase.entity.Notes
import com.example.myapplication.notes.diff.Diff
import com.example.myapplication.notes.diff.DiffUtil

class NotesAdapter(
        private var notes: List<Notes>,
        private var clickLambda: (Notes) -> Unit) : ListAdapter<Notes, NotesViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder =
            NotesViewHolder.create(parent, clickLambda)


    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun submitList(list: MutableList<Notes>?) {
        super.submitList(list)
    }

    fun updateList(newList: List<Notes>) {
        androidx.recyclerview.widget.DiffUtil.calculateDiff(DiffUtil(this.notes, newList), false)
                .dispatchUpdatesTo(this)
        this.notes = newList
    }

    override fun onBindViewHolder(
            holder: NotesViewHolder,
            position: Int,
            payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads[0] as? Bundle
            if (bundle != null) {
                holder.update(bundle)
            }
        }

    }

}
