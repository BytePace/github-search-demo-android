package com.example.test.github_info_loader.core.util


class TimeLocker(
    private val callback: (String) -> Unit,
    private var threadTimeInvoker: ThreadTimerInvoker = ThreadTimerInvoker(callback)
) {

    fun synchronizedCallBackData(s: String) {
        threadTimeInvoker.buffer = s
    }

    fun lockInvoke(seconds: Long) {
        threadTimeInvoker.timer = seconds

        synchronized(threadTimeInvoker) {
            if (threadTimeInvoker.readyToWork) {
                threadTimeInvoker.start()
            } else {
                if (threadTimeInvoker.isAlive) {
                    synchronized(threadTimeInvoker.waiting) {
                        threadTimeInvoker.waiting = true
                    }
                } else {
                    reload()
                }
            }
        }
    }

    private fun reload() {
        threadTimeInvoker = ThreadTimerInvoker(callback)
        threadTimeInvoker.start()
    }
}