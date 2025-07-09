package com.code4galaxy.workmanagerdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.code4galaxy.workmanagerdemo.state.ChainState
import com.code4galaxy.workmanagerdemo.workers.CompressWorker
import com.code4galaxy.workmanagerdemo.workers.DownloadWorker
import com.code4galaxy.workmanagerdemo.workers.UploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChainDemoViewModel @Inject constructor(private val app: Application) :
    AndroidViewModel(app) {
    private val _chainState = MutableStateFlow(ChainState())
    val chainState: StateFlow<ChainState> = _chainState
    val workManager = WorkManager.getInstance(app)

    fun startImageProcessing() {
        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>().build()
        val compressRequest = OneTimeWorkRequestBuilder<CompressWorker>().build()
        val uploadRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()

        val chain =
            workManager.beginUniqueWork("image_chain", ExistingWorkPolicy.REPLACE, downloadRequest)
                .then(compressRequest)
                .then(uploadRequest)
        chain.enqueue()

        // observe work status
        observeStep(downloadRequest.id, "Downloading")
        observeStep(compressRequest.id, "Compressing")
        observeStep(uploadRequest.id, "Uploading")
    }

    private fun observeStep(uUID: UUID, step: String) {
        workManager.getWorkInfoByIdLiveData(uUID).observeForever { info ->
            info?.let {
                _chainState.value = ChainState(
                    step = step,
                    state = it.state,
                    message = it.outputData.getString("result")
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // remove all observers
        workManager.cancelAllWork()
    }
}