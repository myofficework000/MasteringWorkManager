package com.code4galaxy.workmanagerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SyncWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val prefs = applicationContext.getSharedPreferences("local_data", Context.MODE_PRIVATE)
        val message = prefs.getString("message", null)

        return if (message != null) {
            try {
                Log.d("SyncWorker", "Syncing: $message")
                delay(2000) //
                Log.d("SyncWorker", "Sync success")
                prefs.edit().remove("message").apply()
                Result.success()
            } catch (e: Exception) {
                Log.e("SyncWorker", "Sync failed", e)
                Result.retry()
            }
        } else {
            Log.d("SyncWorker", "No message to sync")
            Result.success()
        }
    }
}
