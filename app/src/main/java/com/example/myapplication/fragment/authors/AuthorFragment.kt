package com.example.myapplication.fragment.authors

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.author_fragment.*

class AuthorFragment : Fragment() {

    private var adapter: AuthorAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.author_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val list = AuthorRepository.getAuthors()
        adapter = AuthorAdapter(list) { AuthorData ->
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("Fragment", "music_list")
            intent.putExtra("Author", AuthorData.name)
            startActivity(intent)
        }
        rv_author_list.adapter = adapter
    }
}
