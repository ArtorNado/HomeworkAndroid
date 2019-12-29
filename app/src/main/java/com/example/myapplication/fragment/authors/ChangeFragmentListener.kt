package com.example.myapplication.fragment.authors

import androidx.fragment.app.Fragment

interface ChangeFragmentListener {
    fun changeFragment(containerViewId: Int, instance: Fragment)
}
