package com.example.myapplication.fragment.authors

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AuthorAdapter(
        private var authors: List<AuthorData>,
        private val clickLambda: (AuthorData) -> Unit
) : RecyclerView.Adapter<AuthorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder = AuthorViewHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = authors.size

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.bind(authors[position])
    }

}
