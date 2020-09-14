package com.ericho.example.ui.novel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ericho.example.databinding.RowNovelChapterBinding
import com.ericho.example.ui.novel.chapter.Chapter

class NovelChapterAdapter :
    ListAdapter<Chapter, NovelChapterAdapter.ViewHolder>(itemCallback) {
    interface OnClickListener {
        fun onClick(chapter: Chapter)
    }

    object itemCallback : DiffUtil.ItemCallback<Chapter>() {

        override fun areItemsTheSame(
            oldItem: Chapter,
            newItem: Chapter
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: Chapter,
            newItem: Chapter
        ): Boolean {
            return oldItem == newItem
        }
    }

    var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RowNovelChapterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.setItem(item)
        holder.setOnClickListener(onClickListener)
    }

    class ViewHolder(val binding: RowNovelChapterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: Chapter) {
            binding.item = item
        }

        fun setOnClickListener(onClickListener: OnClickListener?) {
            binding.handler = onClickListener
        }
    }
}