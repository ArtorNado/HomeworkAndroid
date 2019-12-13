package com.example.myapplication.music_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.fragment.OnFragmentListener
import kotlinx.android.synthetic.main.music_list_fragment.*


class MusicListFragment : Fragment(), OnFragmentListener {

    private var adapter: MusicAdapter? = null
    private lateinit var mListener: OnFragmentListener

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.music_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = MusicAdapter(getMusicList()) { MusicData ->
            mListener.onFragmentListener("start", MusicData)
        }
        rv_music_list.adapter = adapter
    }


    private fun getMusicList(): ArrayList<MusicData> {
        val list = MusicRepository.getMusicList()
        val author = arguments?.getString("author") ?: "NULL"
        var newList: ArrayList<MusicData> = ArrayList()
        if (author != "all") {
            list.forEach {
                if (it.author.equals(author))
                    newList.add(it)
            }
        } else newList = list
        return newList
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + "must implement interface")
        }
    }

    override fun onFragmentListener(comand: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentListener(comand: String, musicData: MusicData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        private const val ARG_AUTHOR_NAME = "author"

        fun newInstance(
                author: String = "NULL"
        ): MusicListFragment = MusicListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_AUTHOR_NAME, author)
            }
        }
    }
}
