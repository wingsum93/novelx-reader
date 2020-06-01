package com.ericho.example.ui.novel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ericho.example.databinding.ActivityNovelBinding
import com.ericho.example.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NovelActivity : AppCompatActivity() {


    private val viewModel: NovelViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNovelBinding.inflate(layoutInflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this


    }
}