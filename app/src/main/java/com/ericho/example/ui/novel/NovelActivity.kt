package com.ericho.example.ui.novel

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ericho.example.R
import com.ericho.example.databinding.ActivityNovelBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

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
        viewModel.indexOrDetail.observe(this, Observer {
            Timber.d("indexOrDetail $it")
            if (it) {
                goToHeaderPage()
            } else {
                goToDetailPage()

            }
        })

        //
        viewModel.loadAllInfo(indexLink)

        //
        createFragments()
    }

    private fun goToHeaderPage() {
        Timber.d("goToHeaderPage ")
        supportFragmentManager.beginTransaction()
            .hide(detail!!)
            .show(header!!)
            .commit()
    }

    private fun goToDetailPage() {
        Timber.d("goToDetailPage ")
        supportFragmentManager.beginTransaction()
            .hide(header!!)
            .show(detail!!)
            .commit()
    }

    private fun createFragments() {
        header =
            supportFragmentManager.findFragmentByTag(tag_header) ?: NovelIndexFragment.newInstance(
                viewModel.indexLink
            )
        detail =
            supportFragmentManager.findFragmentByTag(tag_detail) ?: NovelPageFragment.newInstance()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.frameLayout, header!!, tag_header)
            add(R.id.frameLayout, detail!!, tag_detail)
            hide(detail!!)
        }.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                viewModel.switch()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}