package com.example.myapplication.notes.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.notes.NotesData

class DiffUtil(private val oldList: List<NotesData>, private val newList: List<NotesData>) :
        DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
}
