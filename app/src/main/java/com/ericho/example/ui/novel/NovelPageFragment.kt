package com.ericho.example.ui.novel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ericho.example.R
import com.ericho.example.databinding.FragmentNovelPageBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NovelPageFragment : Fragment() {

    private val viewModel: NovelViewModel by sharedViewModel()
    private var binding: FragmentNovelPageBinding? = null

    companion object {

        fun newInstance(): NovelPageFragment {
            val b = Bundle()
//            b.putString(Cc.Key.URL, l)
            val fragment = NovelPageFragment()
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

        viewModel.chapterDisplayModel.observe(this, Observer {
            binding!!.webView.loadUrl(it.link)
        })
    }
}