package com.example.myapplication.fragment.authors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.author_template.view.*

class AuthorViewHolder(
        override val containerView: View,
        private val clickLambda: (AuthorData) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val tv_name = containerView.tv_name
    private val iv_photo = containerView.iv_photo

    fun bind(author: AuthorData) {
        tv_name.text = author.name
        iv_photo.setImageResource(author.photo)
        itemView.setOnClickListener {
            clickLambda(author)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (AuthorData) -> Unit) =
                AuthorViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.author_template, parent, false),
                        clickLambda
                )
    }
}
