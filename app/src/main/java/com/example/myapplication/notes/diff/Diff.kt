package com.example.myapplication.notes.diff

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.notes.constants.Constants
import com.example.myapplication.notes.dataBase.entity.Notes

object Diff : DiffUtil.ItemCallback<Notes>() {

    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean =
            oldItem.description == newItem.description

    override fun getChangePayload(oldItem: Notes, newItem: Notes): Any? {
        val diffBundle = Bundle()
        if (oldItem.title != newItem.title) {
            diffBundle.putString(Constants.DATA.ARG_TITLE, newItem.title)
        }
        if (oldItem.description != newItem.description) {
            diffBundle.putString(Constants.DATA.ARG_DESCRIPTION, newItem.description)
        }
        if (oldItem.latitude != newItem.latitude) {
            diffBundle.putString(Constants.DATA.ARG_LATITUDE, newItem.latitude)
        }
        if (oldItem.longitude != newItem.longitude) {
            diffBundle.putString(Constants.DATA.ARG_LONGITUDE, newItem.longitude)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }

}
