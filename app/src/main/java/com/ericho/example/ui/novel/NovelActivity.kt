package com.ericho.example.ui.novel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ericho.example.R
import com.ericho.example.databinding.ActivityNovelBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NovelActivity : AppCompatActivity() {


    private val viewModel: NovelViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNovelBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_novel)
        binding.vm = viewModel
        binding.lifecycleOwner = this


    }
}