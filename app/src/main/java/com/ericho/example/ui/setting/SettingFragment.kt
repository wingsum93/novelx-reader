package com.ericho.example.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.ericho.example.R

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}