package com.ericho.example.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ericho.example.Cc
import com.ericho.example.R
import com.ericho.example.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.vm = viewModel

        binding?.tvEmpty?.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToIndexFragment(Cc.SampleUrl.LINK_A)
            findNavController().navigate(action)
        }
    }
}