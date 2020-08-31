package com.ericho.example.ui.novel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ericho.example.Cc
import com.ericho.example.R
import com.ericho.example.databinding.FragmentNovelIndexBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * The Index page for novels
 */
class NovelIndexFragment : Fragment() {

    private val viewModel: NovelViewModel by sharedViewModel()
    private var binding: FragmentNovelIndexBinding? = null
    private var adapter: NovelChapterAdapter? = null
    private var url: String? = null

    companion object {
        /**
         * @param l the link of index page
         */
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

        val w = binding?.webView
        w?.loadUrl(viewModel.indexLink)

        binding?.recyclerView?.also {
            it.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            it.adapter = NovelChapterAdapter().also { this.adapter = it }
        }
    }
}