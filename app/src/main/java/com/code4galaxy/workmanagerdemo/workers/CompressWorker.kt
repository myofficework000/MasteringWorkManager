package com.code4galaxy.workmanagerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class CompressWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val path = inputData.getString("downloadedPath")
        delay(1000)

        // compress path
        val compressedPath = path.replace(".jpg", "_compressed.jpg")
        Log.i("CompressWorker", "compressedPath: $compressedPath")
        return Result.success(workDataOf("compressedPath" to compressedPath))
    }
}