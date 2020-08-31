package com.ericho.example

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.ericho.example.ui.novel.NovelIndexFragment
import com.ericho.example.ui.novel.chapter.NovelChapterFragment

class NvFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            NovelIndexFragment::class.qualifiedName -> NovelIndexFragment()
            NovelChapterFragment::class.qualifiedName -> NovelChapterFragment()
            else -> super.instantiate(classLoader, className)
        }

    }
}