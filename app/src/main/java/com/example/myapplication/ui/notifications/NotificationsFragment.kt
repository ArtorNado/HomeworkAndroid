package com.example.myapplication.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private val musicList: ArrayList<Music> = MusicRepository.getDataSource()

    private var adapter: MusicAdapter? = null
    private var pagerAdapter: PagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_music.layoutManager = LinearLayoutManager(context)
        adapter = MusicAdapter(MusicRepository.getDataSource(), context)
        rv_music?.adapter = adapter
    }

    /*fun setRageAdapter(){
        val imageList: List<Int> = arrayListOf(
            R.drawable.uvozh
        )

        viewPager.adapter = ViewPagerAdapter(context, imageList)
        viewPager?.currentItem = 1
    }*/

}