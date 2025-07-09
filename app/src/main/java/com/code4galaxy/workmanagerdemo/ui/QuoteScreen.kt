package com.code4galaxy.workmanagerdemo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.code4galaxy.workmanagerdemo.viewmodel.WorkerViewModel


@Composable
fun QuoteScreen(modifier: Modifier = Modifier) {
    val workerViewModel: WorkerViewModel = hiltViewModel()
    val quote = workerViewModel.quoteFlow.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Text("Latest quote:")
        Text(quote.value, modifier = Modifier.padding(8.dp))

        Row(modifier = Modifier.padding(8.dp)) {
            Button(onClick = { workerViewModel.refreshQuote() }) {
                Text("Refresh")
            }
            Button(onClick = { workerViewModel.scheduleQuoteSync() }) {
                Text("Schedule")
            }
        }
    }
}