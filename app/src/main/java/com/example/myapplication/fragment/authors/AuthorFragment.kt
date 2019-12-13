package com.example.myapplication.fragment.authors

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.music_list.MusicListFragment
import kotlinx.android.synthetic.main.author_fragment.*

class AuthorFragment : Fragment(), ChangeFragmentListener {

    private var adapter: AuthorAdapter? = null
    private lateinit var cListener: ChangeFragmentListener

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
        Log.d("LIST_SIZE", list.size.toString())

        adapter = AuthorAdapter(list) { AuthorData ->
            /*val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("Fragment", "music_list")
            intent.putExtra("Author", AuthorData.name)*/
            cListener.changeFragment(R.id.host_fragment, MusicListFragment.newInstance(AuthorData.name))
        }
        rv_author_list.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ChangeFragmentListener) {
            cListener = context
        } else {
            throw RuntimeException(context.toString() + "must implement interface")
        }
    }

    override fun changeFragment(containerViewId: Int, instance: Fragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        fun newInstance(): AuthorFragment = AuthorFragment()
    }
}
