package com.code4galaxy.workmanagerdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.BackoffPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.code4galaxy.workmanagerdemo.state.RetryState
import com.code4galaxy.workmanagerdemo.workers.RetryUploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RetryWorkerViewModel @Inject constructor(private val app: Application) :
    AndroidViewModel(app) {

    private val _retryState  = MutableStateFlow(RetryState())
    val retryState: StateFlow<RetryState> = _retryState

    private val workManager = WorkManager.getInstance(app)

    fun startRetryingUpload() {
        val input = workDataOf("image_uri" to "/fake_path/image.jpg")

        val request = OneTimeWorkRequestBuilder<RetryUploadWorker>()
            .setInputData(workDataOf("filepath" to input))
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.EXPONENTIAL,
                duration = java.time.Duration.ofSeconds(10)
            ).build()

        workManager.enqueueUniqueWork(
            "retry upload",
            ExistingWorkPolicy.REPLACE,
            request
        )

        workManager.getWorkInfoByIdLiveData(request.id).observeForever { info ->
            info?.let {
                _retryState.value = RetryState(
                    state = info.state,
                    attempts = info.runAttemptCount,
                    message = info.outputData.getString("result")
                )
            }
        }
    }
}