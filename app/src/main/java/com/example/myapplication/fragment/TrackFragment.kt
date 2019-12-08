package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.music_list.MusicData
import com.example.myapplication.shared.SharedViewModel
import kotlinx.android.synthetic.main.track_fragment.*


class TrackFragment : Fragment(), OnFragmentListener {

    private lateinit var mListener: OnFragmentListener
    private lateinit var model: SharedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.track_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val act: MainActivity = activity as MainActivity
        lateinit var musicData: MusicData
        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
            model.destinationFrom.observe(viewLifecycleOwner, Observer {
                musicData = it
                it?.let {
                    tv_album.text = it.album
                    tv_title.text = it.title
                    iv_cover.setImageResource(it.cover)
                }
            })
        }
        btn_next.setOnClickListener {
            mListener.onFragmentListener("next")
        }
        btn_previous.setOnClickListener {
            mListener.onFragmentListener("previous")
        }
        btn_pause.setOnClickListener {
            mListener.onFragmentListener("pause")
        }
    }

    companion object {

        fun newInstance(): TrackFragment = TrackFragment()

    }

    override fun onFragmentListener(comand: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnFragmentListener(comand: String, musicData: MusicData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + "must implement interface")
        }
    }
}
