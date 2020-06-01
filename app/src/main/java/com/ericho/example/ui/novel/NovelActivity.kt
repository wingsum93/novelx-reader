package com.ericho.example.ui.novel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericho.example.R
import com.ericho.example.databinding.ActivityNovelBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NovelActivity : AppCompatActivity() {


    private val viewModel: NovelViewModel by viewModel()
    private var header: Fragment? = null
    private var detail: Fragment? = null

    val tag_header = "tag_header"
    val tag_detail = "tag_detail"

    val indexLink = "https://t.uukanshu.com/book.aspx?id=41496"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNovelBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_novel)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)
        // register actions
        viewModel

        //
        viewModel.loadAllInfo(indexLink)

        //
        createFragments()
    }

    private fun createFragments() {
        header =
            supportFragmentManager.findFragmentByTag(tag_header) ?: NovelIndexFragment.newInstance(
                viewModel.indexLink
            )
        detail =
            supportFragmentManager.findFragmentByTag(tag_detail) ?: NovelPageFragment.newInstance()

        supportFragmentManager.beginTransaction().apply {
            add(header!!, tag_header)
            add(detail!!, tag_detail)
            hide(detail!!)
        }.commit()

    }
}