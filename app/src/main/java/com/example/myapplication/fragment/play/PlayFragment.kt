package com.example.myapplication.fragment.play

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.constants.Constants
import com.example.myapplication.fragment.OnFragmentListener
import com.example.myapplication.fragment.track_info.TrackFragment
import com.example.myapplication.music_list.MusicData
import com.example.myapplication.notification.NotificationChanges
import com.example.myapplication.shared.SharedViewModel
import com.example.myapplication.track_status.TrackStatus
import kotlinx.android.synthetic.main.play_fragment.*

class PlayFragment : Fragment(), OnFragmentListener {

    private lateinit var mListener: OnFragmentListener
    private lateinit var model: SharedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.play_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + "must implement interface")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultImage()
        var musicData: MusicData? = null
        tv_test.text = NotificationChanges.musicData.album
        iv_cover.setImageResource(NotificationChanges.musicData.cover)
        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
            model.destinationFrom.observe(viewLifecycleOwner, Observer {
                musicData = it
                it?.let {
                    tv_test.text = it.album
                    iv_cover.setImageResource(it.cover)
                    if (TrackStatus.status == Constants.ACTION.START) btn_pause.setBackgroundResource(R.drawable.ic_pause_circle_filled_24px)
                    else btn_pause.setBackgroundResource(R.drawable.ic_play_circle_filled_24px)
                }
            })
        }
        btn_next.setOnClickListener {
            btnNextAction()
        }
        btn_pause.setOnClickListener {
            btnPauseAction()
        }
        view.setOnClickListener {
            musicData?.let { it1 -> track(it1) }
        }
    }

    private fun defaultImage() {
        if (TrackStatus.status == Constants.ACTION.START) btn_pause.setBackgroundResource(R.drawable.ic_pause_circle_filled_24px)
        else btn_pause.setBackgroundResource(R.drawable.ic_play_circle_filled_24px)
        btn_next.setBackgroundResource(R.drawable.ic_skip_next_24px)
    }

    private fun btnNextAction() {
        mListener.onFragmentListener(Constants.ACTION.NEXT_TRACK)
    }

    private fun btnPauseAction() {
        mListener.onFragmentListener(Constants.ACTION.PAUSE)
        if (TrackStatus.status == Constants.ACTION.START) btn_pause.setBackgroundResource(R.drawable.ic_pause_circle_filled_24px)
        else btn_pause.setBackgroundResource(R.drawable.ic_play_circle_filled_24px)
    }

    private fun track(musicData: MusicData) {
        if (musicData.title != "") {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(
                        R.id.host_fragment,
                        TrackFragment.newInstance(),
                        "tag"
                )
                addToBackStack(TrackFragment::class.java.name)
                commit()
            }
        }
    }

    override fun onFragmentListener(comand: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentListener(comand: String, musicData: MusicData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
