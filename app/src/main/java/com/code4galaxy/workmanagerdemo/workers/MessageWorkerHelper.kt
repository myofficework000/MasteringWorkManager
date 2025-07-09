package com.code4galaxy.workmanagerdemo.workers

import android.content.Context
import androidx.work.*

import java.util.concurrent.TimeUnit

object MessageWorkerHelper {
    fun saveToLocal(context: Context, data: String) {
        val prefs = context.getSharedPreferences("local_data", Context.MODE_PRIVATE)
        prefs.edit().putString("message", data).apply()
    }

    fun scheduleSyncWorker(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(constraints)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}
