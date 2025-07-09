package com.code4galaxy.workmanagerdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.code4galaxy.workmanagerdemo.workers.QuoteWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class WorkerViewModel @Inject constructor(private val app: Application) :
    AndroidViewModel(app) {

    private val _quoteFlow = MutableStateFlow("No quotes yet...")
    val quoteFlow: StateFlow<String> = _quoteFlow.asStateFlow()

    fun scheduleQuoteSync() {
        // STEP 1: Create constraints
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        // STEP 2: Create a periodic work request
        val workRequest = PeriodicWorkRequestBuilder<QuoteWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        // STEP 3: Create WorkManager instance and enqueue the work request
        WorkManager.Companion.getInstance(app).enqueueUniquePeriodicWork(
            uniqueWorkName = "quoteSync",
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    fun refreshQuote() {
        val pref = app.getSharedPreferences("quotes", Application.MODE_PRIVATE)
        _quoteFlow.value = pref.getString("quote", "No quotes yet...") ?: "No quotes yet..."
    }
}