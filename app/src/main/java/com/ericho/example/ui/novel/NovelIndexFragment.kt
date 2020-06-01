package com.ericho.example.ui.novel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericho.example.Cc
import com.ericho.example.R
import com.ericho.example.databinding.FragmentNovelIndexBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NovelIndexFragment : Fragment() {

    private val viewModel: NovelViewModel by sharedViewModel()

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
        val binding = DataBindingUtil.inflate<FragmentNovelIndexBinding>(
            inflater,
            R.layout.fragment_novel_index,
            container,
            false
        )
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}