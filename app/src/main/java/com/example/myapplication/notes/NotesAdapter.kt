package com.example.myapplication.notes

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.notes.diff.Diff
import com.example.myapplication.notes.diff.DiffUtil

class NotesAdapter(
        private var notes: ArrayList<NotesData>,
        private var clickLambda: (NotesData) -> Unit) : ListAdapter<NotesData, NotesViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder =
            NotesViewHolder.create(parent, clickLambda)


    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun submitList(list: MutableList<NotesData>?) {
        super.submitList(list)
    }

    fun updateList(newList: ArrayList<NotesData>) {
        androidx.recyclerview.widget.DiffUtil.calculateDiff(DiffUtil(this.notes, newList), false)
                .dispatchUpdatesTo(this)
        this.notes.clear()
        this.notes.addAll(newList)
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
