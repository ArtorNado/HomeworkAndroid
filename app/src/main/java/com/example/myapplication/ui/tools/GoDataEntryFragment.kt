package com.example.myapplication.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.ui.userProfile.DataEntryFragment
import kotlinx.android.synthetic.main.fragment_go_data_entry.view.*

class GoDataEntryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_go_data_entry, container, false)
        view.btn_go?.setOnClickListener {
            nextFragment()
        }
        return view
    }

    private fun nextFragment() {
        val fragmentDataEntry = DataEntryFragment()
        fragmentManager.also {
            it?.beginTransaction()?.apply {
                setCustomAnimations(
                    R.anim.slide_in_left,
                    R.anim.slide_in_right
                )
                replace(R.id.nav_host_fragment, fragmentDataEntry, "tag")
                addToBackStack(DataEntryFragment::class.java.name)
                commit()
            }
        }
    }

    companion object {
        fun newInstance(): GoDataEntryFragment = GoDataEntryFragment()
    }

}
