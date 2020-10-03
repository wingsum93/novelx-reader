package com.ericho.example.other

import android.content.Context
import android.content.SharedPreferences
import com.ericho.example.ui.novel.chapter.Chapter
import com.google.gson.Gson

class NovelSharePreferenceHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("novel", Context.MODE_PRIVATE)
    private val gson = Gson()


    fun getCurrentChapter(key: String): Chapter? {
        val value = sharedPreferences.getString(key, null)
        return value?.let { gson.fromJson(it, Chapter::class.java) }
    }

    fun saveCurrentChapter(key: String, currentChapter: Chapter) {
        sharedPreferences.edit()
            .putString(key, gson.toJson(currentChapter))
            .apply()
    }
}