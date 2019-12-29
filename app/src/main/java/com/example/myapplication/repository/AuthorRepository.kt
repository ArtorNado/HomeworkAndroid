package com.example.myapplication.repository

import com.example.myapplication.R
import com.example.myapplication.fragment.authors.AuthorData

object AuthorRepository {

    fun getAuthors(): ArrayList<AuthorData> = arrayListOf(
            AuthorData("May Wave$", R.drawable.may_wave_profile),
            AuthorData("Big Baby Tape", R.drawable.tape_profile),
            AuthorData("Руки вверх", R.drawable.ryki_vverh_profile),
            AuthorData("Макс Корж", R.drawable.korzh_profile)
    )
}
