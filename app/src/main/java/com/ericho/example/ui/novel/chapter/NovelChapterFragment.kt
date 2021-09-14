package com.ericho.example.ui.novel.chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ericho.example.R
import com.ericho.example.databinding.FragmentNovelPageBinding
import com.ericho.example.ui.novel.NovelViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * The reading page for novels chapters
 */
class NovelChapterFragment : Fragment() {

    private val viewModel: NovelViewModel by sharedViewModel()

    private val args: NovelChapterFragmentArgs by navArgs()
    private var binding: FragmentNovelPageBinding? = null
    private lateinit var chapter: Chapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chapter = args.chapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_novel_page,
            container,
            false
        )
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.vm = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        viewModel.chapterLiveData.observe(viewLifecycleOwner, Observer {
            //go to that chapter
            binding?.webView?.loadUrl(it.link)
        })
    }
}