package com.example.myapplication.notes.mainFragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.notes.constants.Constants
import com.example.myapplication.notes.dataBase.entity.Notes
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.notes_list_template.view.*
import kotlin.random.Random

class NotesViewHolder(
        override val containerView: View,
        private val clickLambda: (Notes) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val tvTitle = containerView.tv_title
    private val tvDescription = containerView.tv_description
    private val tvLongitude = containerView.tv_longitude
    private val tvLatitude = containerView.tv_latitude
    private var cv = containerView.card_view

    fun bind(notes: Notes) {
        setRandomColor()
        tvTitle.text = notes.title
        tvDescription.text = notes.description
        tvLatitude.text = notes.latitude
        tvLongitude.text = notes.longitude
        itemView.setOnClickListener {
            clickLambda(notes)
        }
    }

    fun setRandomColor() {
        val colorList = listOf(Color.BLUE, Color.GRAY, Color.GREEN)
        cv.setCardBackgroundColor(colorList[Random.nextInt(colorList.size)])
    }

    fun update(bundle: Bundle) {
        tvTitle.text = bundle.getString(Constants.DATA.ARG_TITLE) ?: "NULL"
        tvDescription.text = bundle.getString(Constants.DATA.ARG_DESCRIPTION) ?: "NULL"
        tvLatitude.text = bundle.getString(Constants.DATA.ARG_LATITUDE) ?: "NULL"
        tvLongitude.text = bundle.getString(Constants.DATA.ARG_LONGITUDE) ?: "NULL"
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Notes) -> Unit) =
                NotesViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.notes_list_template, parent, false),
                        clickLambda
                )
    }
}
