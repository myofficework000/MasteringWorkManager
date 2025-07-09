package com.code4galaxy.workmanagerdemo.state

import androidx.work.WorkInfo

data class ChainState(
    val step: String = "",
    val state: WorkInfo.State = WorkInfo.State.ENQUEUED,
    val message: String? = null
)
