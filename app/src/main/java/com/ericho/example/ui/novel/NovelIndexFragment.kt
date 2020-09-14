package com.ericho.example.ui.novel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ericho.example.Cc
import com.ericho.example.R
import com.ericho.example.databinding.FragmentNovelIndexBinding
import com.ericho.example.ui.novel.chapter.Chapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * The Index page for novels
 */
class NovelIndexFragment : Fragment() {

    private val viewModel: NovelViewModel by sharedViewModel()
    private var binding: FragmentNovelIndexBinding? = null
    private var adapter: NovelChapterAdapter? = null
    private var url: String? = null
    private var KEY_URL = "novelUrl"

    companion object {
        /**
         * @param l the link of index page
         */
        @Deprecated("")
        fun newInstance(l: String): NovelIndexFragment {
            val b = Bundle()
            b.putString(Cc.Key.URL, l)
            val fragment = NovelIndexFragment()
            fragment.arguments = b
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get data
        val b = requireArguments()
        url = b.getString(KEY_URL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_novel_index,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.vm = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        binding?.recyclerView?.also {
            it.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            it.setHasFixedSize(true)
            it.adapter = NovelChapterAdapter().also {
                adapter
                this.adapter = it
                adapter?.onClickListener = object : NovelChapterAdapter.OnClickListener {
                    override fun onClick(chapter: Chapter) {
                        findNavController().navigate(R.id.action_indexFragment_to_chapterFragment)
                    }
                }
            }
        }

        //
        viewModel.novel.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it.chapters)
        })

        viewModel.loadAllInfo(url!!)
    }
}