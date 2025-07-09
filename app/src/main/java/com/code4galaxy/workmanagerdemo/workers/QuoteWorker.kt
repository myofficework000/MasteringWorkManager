package com.code4galaxy.workmanagerdemo.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class QuoteWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        // Do the work like long running operations
        val quote = "RandomQuote ${System.currentTimeMillis()}"
        val pref = applicationContext.getSharedPreferences("quotes", Context.MODE_PRIVATE)

        val result = pref.edit().putString("quote", quote).commit()
        return if (result) Result.success() else Result.failure()
    }
}