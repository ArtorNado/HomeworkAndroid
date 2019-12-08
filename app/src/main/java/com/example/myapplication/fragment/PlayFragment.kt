package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.music_list.MusicData
import com.example.myapplication.music_list.NotificationChanges
import com.example.myapplication.shared.SharedViewModel
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
        var musicData: MusicData? = null
        tv_test.text = NotificationChanges.musicData.album
        iv_cover.setImageResource(NotificationChanges.musicData.cover)
        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
            model.destinationFrom.observe(viewLifecycleOwner, Observer {
                musicData = it
                it?.let {
                    Log.d("CHANGE", "CHANGE")
                    tv_test.text = it.album
                    iv_cover.setImageResource(it.cover)
                }
            })
        }
        btn_next.setOnClickListener {
            mListener.onFragmentListener("next")
        }
        btn_pause.setOnClickListener {
            mListener.onFragmentListener("pause")
        }
        view.setOnClickListener {
            musicData?.let { it1 -> track(it1) }
        }
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

    override fun OnFragmentListener(comand: String, musicData: MusicData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
