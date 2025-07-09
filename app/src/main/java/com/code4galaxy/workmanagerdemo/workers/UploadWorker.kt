package com.code4galaxy.workmanagerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val path = inputData.getString("compressedPath") ?: return Result.failure()

        delay(1000)
        Log.i("UploadWorker", "Success: Upload image from $path")
        return Result.success(workDataOf("result" to "Upload success"))
    }
}