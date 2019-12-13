package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.music_list.Constants
import com.example.myapplication.music_list.MusicData
import com.example.myapplication.music_list.TrackStatus
import com.example.myapplication.shared.SharedViewModel
import kotlinx.android.synthetic.main.track_fragment.*


class TrackFragment : Fragment(), OnFragmentListener {

    private lateinit var mListener: OnFragmentListener
    private lateinit var model: SharedViewModel
    private lateinit var pause: String

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.track_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + "must implement interface")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        defaultImage()
        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
            model.destinationFrom.observe(viewLifecycleOwner, Observer { it ->
                it?.let {
                    tv_album.text = it.album
                    tv_title.text = it.title
                    iv_cover.setImageResource(it.cover)
                }
            })
        }
        btn_pause.setOnClickListener {
            btnPauseAction()
        }
        btn_next.setOnClickListener {
            btnNextAction()
        }
        btn_previous.setOnClickListener {
            btnPreviousAction()
        }
    }

    private fun btnPauseAction() {
        pause = btn_pause.background.toString()
        mListener.onFragmentListener(Constants.ACTION.PAUSE)
        if (TrackStatus.status == Constants.ACTION.START) btn_pause.setBackgroundResource(R.drawable.ic_pause_circle_filled_24px)
        else btn_pause.setBackgroundResource(R.drawable.ic_play_circle_filled_24px)
    }

    private fun btnNextAction() {
        mListener.onFragmentListener(Constants.ACTION.NEXT_TRACK)
    }

    private fun btnPreviousAction() {
        mListener.onFragmentListener(Constants.ACTION.PREV_TRACK)
    }

    private fun defaultImage() {
        if (TrackStatus.status == Constants.ACTION.START) btn_pause.setBackgroundResource(R.drawable.ic_pause_circle_filled_24px)
        else btn_pause.setBackgroundResource(R.drawable.ic_play_circle_filled_24px)
    }

    override fun onFragmentListener(comand: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentListener(comand: String, musicData: MusicData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance(): TrackFragment = TrackFragment()
    }
}
