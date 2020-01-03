package com.example.myapplication.notes.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.notes.dataBase.entity.Notes

class DiffUtil(private val oldList: List<Notes>, private val newList: List<Notes>) :
        DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
}
