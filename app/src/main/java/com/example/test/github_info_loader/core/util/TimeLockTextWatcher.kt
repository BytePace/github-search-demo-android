package com.example.test.github_info_loader.core.util

import android.text.Editable
import android.text.TextWatcher

class TimeLockTextWatcher(private val callback: (String)-> Unit): TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        callback.invoke(s.toString())
    }
}