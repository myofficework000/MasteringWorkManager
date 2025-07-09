package com.code4galaxy.workmanagerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class RetryUploadWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val filepath = inputData.getString("filepath")
        Log.i("RetryUploadWorker", "filepath: $filepath")

        // Do the work like long running operations
        val attempt = runAttemptCount
        Log.i("RetryUploadWorker", "Attempt: $attempt")

        // Simulate the network
        delay(1000)
        val success = (1..10).random() > 7

        return if (success) {
            Log.i("RetryUploadWorker", "Upload success")
            val output = workDataOf("result" to "Upload success after $attempt attempts")
            Result.success(output)
        } else {
            Log.i("RetryUploadWorker", "Upload failure")
            if (attempt >= 3) {
                Result.failure()
            } else {
                Result.retry()
            }
        }
    }
}