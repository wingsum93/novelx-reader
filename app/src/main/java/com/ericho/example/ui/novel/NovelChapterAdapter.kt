package com.ericho.example.ui.novel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ericho.example.databinding.RowNovelChapterBinding
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

        val inflater = LayoutInflater.from(parent.context)
        val binding = RowNovelChapterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.setItem(item)
    }

    class ViewHolder(val binding: RowNovelChapterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: ChapterDisplayModel) {
            binding.item = item
        }
    }
}