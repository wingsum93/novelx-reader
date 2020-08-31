package com.ericho.example.ui.novel

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ericho.example.ui.novel.chapter.ChapterDisplayModel

class NovelChapterAdapter :
    ListAdapter<ChapterDisplayModel, NovelChapterAdapter.ViewHolder>(itemCallback) {

    object itemCallback : DiffUtil.ItemCallback<ChapterDisplayModel>() {

        override fun areItemsTheSame(
            oldItem: ChapterDisplayModel,
            newItem: ChapterDisplayModel
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ChapterDisplayModel,
            newItem: ChapterDisplayModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun setItem() {

        }
    }
}