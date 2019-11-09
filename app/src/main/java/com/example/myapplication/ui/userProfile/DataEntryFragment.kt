package com.example.myapplication.ui.userProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_data_entry.*
import kotlinx.android.synthetic.main.fragment_data_entry.view.*

class DataEntryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_data_entry, container, false)
        view.btn_send?.setOnClickListener {
            val firstName = et_firstName.text.toString()
            val secondName = et_secondName.text.toString()
            val age = Integer.parseInt(et_age.text.toString())
            nextFragment(firstName, secondName, age)
        }
        return view
    }

    private fun nextFragment(firstName: String, secondName: String, age: Int) {
        fragmentManager.also {
            it?.beginTransaction()?.apply {
                setCustomAnimations(
                    R.anim.nav_default_exit_anim,
                    R.anim.nav_default_enter_anim
                )
                replace(
                    R.id.nav_host_fragment,
                    UserProfileFragment.newInstance(firstName, secondName, age),
                    "tag"
                )
                fragmentManager?.popBackStack()
                addToBackStack(UserProfileFragment::class.java.name)
                commit()
            }
        }
    }
}
