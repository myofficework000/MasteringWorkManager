package com.code4galaxy.workmanagerdemo.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class DownloadWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        delay(1000)
        val fakeFilePath = "/storage/emulated/0/Download/image.jpg"
        return Result.success(workDataOf("downloadedPath" to fakeFilePath))
    }
}