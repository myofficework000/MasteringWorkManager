package com.code4galaxy.workmanagerdemo.state

import androidx.work.WorkInfo

data class RetryState(
    val state: WorkInfo.State = WorkInfo.State.ENQUEUED,
    val attempts: Int = 0,
    val message: String? = null
)
