package com.example.test.github_info_loader.core.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ThreadTimerInvoker(
    private val callback: (String) -> Unit,
    var waiting: Boolean = true,
    var buffer: String = "",
    var readyToWork: Boolean = true,
    var timer: Long = 1000
) : Thread() {
    override fun run() {
        readyToWork = false
        while (waiting) {
            synchronized(waiting) {
                waiting = false
            }
            sleep(timer)
        }
        callback.invokeOnMainThread(buffer)

        synchronized(readyToWork) {
            readyToWork = false
        }
    }

    fun ((String) -> Unit).invokeOnMainThread(s: String) {
        GlobalScope.launch(context = Dispatchers.Main) {
            this@invokeOnMainThread(s)
        }
    }
}