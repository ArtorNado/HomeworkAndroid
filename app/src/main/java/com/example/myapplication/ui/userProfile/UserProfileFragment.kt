package com.example.myapplication.ui.userProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_profile.*

class UserProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstName = arguments?.getString("firstName").toString()
        val secondName = arguments?.getString("secondName").toString()
        val age = arguments?.getInt("age") ?: 0
        tv_firstName.text = firstName
        tv_secondName.text = secondName
        tv_age.text = age.toString()
    }

    companion object {

        private const val ARG_FIRST_NAME = "firstName"
        private const val ARG_SECOND_NAME = "secondName"
        private const val ARG_AGE = "age"

        fun newInstance(
            firstName: String = "NULL",
            secondName: String = "NULL",
            age: Int = 0
        ): UserProfileFragment = UserProfileFragment().apply {
            arguments = Bundle().apply {
                putString(FIRST_NAME, firstName)
                putString(SECOND_NAME, secondName)
                putInt(AGE, age)
            }
        }
    }
}
